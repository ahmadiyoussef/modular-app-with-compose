package com.example.search.ui.screens.recipe_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.utils.Resource
import com.example.common.utils.UiText
import com.example.search.domain.model.RecipeDetails
import com.example.search.domain.model.RecipeDomainModel
import com.example.search.domain.use_cases.GetRecipeDetailUseCase
import com.example.search.ui.screens.recipe_list.RecipeList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class RecipeDetailViewModel @Inject constructor(
    private val recipeDetailUseCase: GetRecipeDetailUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(RecipeDetail.UiState())
    val uiState: StateFlow<RecipeDetail.UiState> get() = _uiState.asStateFlow()

    fun onEvent(event: RecipeDetail.Event) {
      when(event){
          is RecipeDetail.Event.FetchRecipeDetails -> {
              recipeDetails(event.id)
          }
      }
    }

    fun recipeDetails(id: String) = viewModelScope.launch {
        recipeDetailUseCase.invoke(id)
            .onEach { result ->
                when (result) {
                    is Resource.Loading -> {
                        _uiState.update { RecipeDetail.UiState(isLoading = true) }
                    }

                    is Resource.Error -> {
                        _uiState.update {
                            RecipeDetail.UiState(
                                error = UiText.RemoteString(
                                    result.message.toString()
                                )
                            )
                        }
                    }

                    is Resource.Success -> {
                        _uiState.update { RecipeDetail.UiState(data = result.data) }

                    }
                }

            }
    }

}


object RecipeDetail {
    data class UiState(
        val isLoading: Boolean = false,
        val error: UiText = UiText.Idle,
        val data: RecipeDetails? = null
    )

    sealed interface Navigation {

    }

    sealed interface Event {
    data class FetchRecipeDetails(val id: String): Event
    }


}