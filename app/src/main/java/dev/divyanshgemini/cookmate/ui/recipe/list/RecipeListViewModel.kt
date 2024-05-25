package dev.divyanshgemini.cookmate.ui.recipe.list

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dev.divyanshgemini.cookmate.data.model.Recipe
import dev.divyanshgemini.cookmate.data.repository.RecipeRepository
import kotlinx.coroutines.launch

class RecipeListViewModel(application: Application): AndroidViewModel(application) {
    private val repository: RecipeRepository = RecipeRepository()
    private var recipesLiveData: MutableLiveData<List<Recipe>> = MutableLiveData()

    fun getRecipes(query: String): LiveData<List<Recipe>> {
        Log.i("TAG", "RecipeViewModel :: getRecipes")
        viewModelScope.launch {
            val result = repository.getSearchedRecipes(query).await()
            recipesLiveData.value = result.value
        }

        Log.i("TAG", "RecipeListViewModel :: getRecipes: returning ${recipesLiveData.value}")
        return recipesLiveData
    }
}