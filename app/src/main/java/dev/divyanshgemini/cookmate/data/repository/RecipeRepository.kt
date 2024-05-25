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

class RecipeRepository {
    private val recipes = mutableListOf<Recipe>()
    private val mutableLiveData = MutableLiveData<List<Recipe>>()

    suspend fun getSearchedRecipes(query: String): Deferred<MutableLiveData<List<Recipe>>> = withContext(Dispatchers.IO) {
        async {
            Log.i("TAG", "RecipeRepository :: getSearchedRecipes: $query")
            val apiKey = BuildConfig.SPOONACULAR_API_KEY
            val apiService: SpoonacularApiService = RetrofitInstance.api

            try {
                Log.i("TAG", "RecipeRepository :: getSearchedRecipes: try")
                val response = apiService.getRecipes(apiKey, query, "Vegetarian", "popularity", "eggs")

                if (response.recipes.isNotEmpty()) {
                    Log.i("TAG", "RecipeRepository :: getSearchedRecipes: response --> $response")
                    recipes.clear()
                    recipes.addAll(response.recipes)
                    mutableLiveData.postValue(recipes)
                }
                else
                    Log.i("TAG", "RecipeRepository :: getSearchedRecipes: response is empty")
            } catch (e: Exception) {
                Log.e("RecipeRepository", "getSearchedRecipes failed", e)
            }

            Log.i("TAG", "RecipeRepository :: getSearchedRecipes: returning ${mutableLiveData.value}")
            return@async mutableLiveData
        }
    }
}