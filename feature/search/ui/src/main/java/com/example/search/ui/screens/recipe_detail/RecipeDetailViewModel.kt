package com.example.search.ui.screens.recipe_detail

import androidx.lifecycle.ViewModel
import com.example.search.domain.use_cases.GetRecipeDetailUseCase
import javax.inject.Inject

class RecipeDetailViewModel @Inject constructor(
    private val recipeDetailUseCase: GetRecipeDetailUseCase
) : ViewModel() {


}