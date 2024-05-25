package dev.divyanshgemini.cookmate.data.network

import dev.divyanshgemini.cookmate.data.model.RecipeResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SpoonacularApiService {
    @GET("recipes/complexSearch")
    suspend fun getRecipes(
        @Query("apiKey") apiKey: String,
        @Query("query") query: String,
        @Query("diet") diet: String,
        @Query("sort") sort: String,
        @Query("excludeIngredients") excludeIngredients: String
    ): RecipeResponse
}