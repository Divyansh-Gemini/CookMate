package dev.divyanshgemini.cookmate.data.network

import dev.divyanshgemini.cookmate.data.model.RecipeInformationResponse
import dev.divyanshgemini.cookmate.data.model.RecipeResponse
import retrofit2.http.GET
import retrofit2.http.Path
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

    @GET("recipes/{id}/information")
    suspend fun getRecipeInformation(
        @Path("id") id: Int,
        @Query("apiKey") apiKey: String,
        @Query("includeNutrition") includeNutrition: Boolean,
        @Query("addWinePairing") addWinePairing: Boolean,
        @Query("addTasteData") addTasteData	: Boolean
    ): RecipeInformationResponse
}