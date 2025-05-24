package com.example.search.ui.screens.recipe_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.utils.Resource
import com.example.common.utils.UiText
import com.example.search.domain.model.RecipeDomainModel
import com.example.search.domain.use_cases.GetAllRecipeUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class RecipeListViewModel @Inject constructor(
    private val getAllRecipeUseCase: GetAllRecipeUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(RecipeList.UiState())
    val uiState: StateFlow<RecipeList.UiState> get() = _uiState.asStateFlow()




    fun search(q: String) = viewModelScope.launch {
        getAllRecipeUseCase.invoke(q).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _uiState.update { RecipeList.UiState(isLoading = true) }
                }

                is Resource.Success -> {
                    _uiState.update { RecipeList.UiState(data = result.data) }
                }

                is Resource.Error -> {
                    _uiState.update { RecipeList.UiState(error = UiText.RemoteString(result.message.toString())) }
                }
            }
        }
    }



    fun onEvent(event: RecipeList.Event){
        when(event){
            is RecipeList.Event.SearchRecipe -> {
                search(event.q)
            }
        }
    }
}

object RecipeList {
    data class UiState(
        val isLoading: Boolean = false,
        val error: UiText = UiText.Idle,
        val data: List<RecipeDomainModel>? = null
    )

    sealed interface Navigation {
        data class GoToRecipeDetails(val id: String) : Navigation
    }

    sealed interface Event {
        data class SearchRecipe(val q: String): Event
    }
}