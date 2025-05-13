package com.example.search.data.remote

import com.example.search.data.model.RecipeDetailResponse
import com.example.search.data.model.RecipeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApiService {


    //https://themealdb.com/api/json/v1/1/search.php?s=chicken
    @GET("api/json/v1/1/search.php")
    suspend fun getRecipes(
        @Query("s") s:String
    ): Response<RecipeResponse>

    @GET("api/json/v1/1/lookup.php")
    suspend fun getRecipeDetail(

        @Query("i") i:String
    ): Response<RecipeDetailResponse>
}