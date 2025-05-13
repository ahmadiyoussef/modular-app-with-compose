package com.example.search.data.mappers

import com.example.search.data.model.RecipeDataModel
import com.example.search.domain.model.RecipeDomainModel

fun List<RecipeDataModel>.toDomain(): List<RecipeDomainModel> = map{
    RecipeDomainModel(
        idMeal = it.idMeal,
        strArea = it.strArea,
        strMeal = it.strMeal,
        strMealThumb = it.strMealThumb,
        strCategory = it.strCategory,
        strTags = it.strTags ?: "",
        strYoutube = it.strYoutube ?: "",
        strInstructions = it.strInstructions
    )
}