package dev.divyanshgemini.cookmate.ui.recipe.list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.divyanshgemini.cookmate.data.model.Recipe
import dev.divyanshgemini.cookmate.data.repository.RecipeRepository
import kotlinx.coroutines.launch

class RecipeListViewModel: ViewModel() {
    private val repository: RecipeRepository = RecipeRepository()
    private var recipesLiveData: MutableLiveData<List<Recipe>> = MutableLiveData()

    // Function to fetch recipes from repository
    fun getRecipes(query: String): LiveData<List<Recipe>> {
        viewModelScope.launch {
            val result = repository.getSearchedRecipes(query).await()
            recipesLiveData.value = result.value
        }

        return recipesLiveData
    }
}