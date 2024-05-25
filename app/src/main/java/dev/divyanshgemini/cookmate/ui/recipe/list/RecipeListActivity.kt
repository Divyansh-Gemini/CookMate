package dev.divyanshgemini.cookmate.ui.recipe.list

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import dev.divyanshgemini.cookmate.databinding.ActivityRecipeListBinding

class RecipeListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecipeListBinding
    private val recipeViewModel: RecipeListViewModel by viewModels()
    private lateinit var recipeAdapter: RecipeListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i("TAG", "RecipeListActivity :: onCreate")
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()

        recipeViewModel.getRecipes("").observe(this) { recipes ->
            if (recipes != null) {
                Log.i("TAG", "RecipeListActivity :: onCreate: recipes --> $recipes")
                recipeAdapter.setRecipes(recipes)
            }
            else
                Log.i("TAG", "RecipeListActivity :: onCreate: recipes is null")
        }
    }

    private fun setupRecyclerView() {
        Log.i("TAG", "RecipeListActivity :: setupRecyclerView")
        recipeAdapter = RecipeListAdapter()
        binding.recyclerView.apply {
            adapter = recipeAdapter
            layoutManager = LinearLayoutManager(this@RecipeListActivity)
        }
    }
}
