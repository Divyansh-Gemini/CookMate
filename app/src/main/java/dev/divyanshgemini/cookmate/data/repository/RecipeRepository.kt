package dev.divyanshgemini.cookmate.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import dev.divyanshgemini.cookmate.data.model.Recipe
import dev.divyanshgemini.cookmate.data.network.RetrofitInstance
import dev.divyanshgemini.cookmate.data.network.SpoonacularApiService
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import dev.divyanshgemini.cookmate.BuildConfig
import dev.divyanshgemini.cookmate.data.model.RecipeInformationResponse

class RecipeRepository {
    private val recipes = mutableListOf<Recipe>()
    private val recipesMutableLiveData = MutableLiveData<List<Recipe>>()

    private val recipeInformation = mutableListOf<RecipeInformationResponse>()
    private val recipeInformationLiveData = MutableLiveData<List<RecipeInformationResponse>>()

    private val apiKey = BuildConfig.SPOONACULAR_API_KEY
    private val apiService: SpoonacularApiService = RetrofitInstance.api
    private val TAG = "RecipeRepository"

    suspend fun getSearchedRecipes(query: String): Deferred<MutableLiveData<List<Recipe>>> = withContext(Dispatchers.IO) {
        async {
            try {
                val response = apiService.getRecipes(apiKey, query, "Vegetarian", "popularity", "eggs")

                if (response.recipes.isNotEmpty()) {
                    recipes.clear()
                    recipes.addAll(response.recipes)
                    recipesMutableLiveData.postValue(recipes)
                }
            } catch (e: Exception) {
                Log.e(TAG, "getSearchedRecipes failed", e)
            }

            return@async recipesMutableLiveData
        }
    }

    suspend fun getRecipeInformation(id: Int): Deferred<MutableLiveData<List<RecipeInformationResponse>>> = withContext(Dispatchers.IO) {
        async {
            try {
                val response = apiService.getRecipeInformation(id, apiKey,
                    includeNutrition = false,
                    addWinePairing = false,
                    addTasteData = false
                )

                recipeInformation.clear()
                recipeInformation.add(response)
                recipeInformationLiveData.postValue(listOf(response))
            } catch (e: Exception) {
                Log.e(TAG, "getRecipeInformation failed", e)
            }

            return@async recipeInformationLiveData
        }
    }
}