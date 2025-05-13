package com.example.search.domain.repository

import com.example.search.domain.model.RecipeDomainModel
import com.example.search.domain.model.RecipeDetails

interface SearchRepository {

    suspend fun getRecipes(s: String): Result<List<RecipeDomainModel>>

    suspend fun getRecipeDetail(id: String): Result<List<RecipeDetails>>
}