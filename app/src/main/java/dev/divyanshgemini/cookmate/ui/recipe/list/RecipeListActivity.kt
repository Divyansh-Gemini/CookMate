package dev.divyanshgemini.cookmate.ui.recipe.list

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import dev.divyanshgemini.cookmate.databinding.ActivityRecipeListBinding

class RecipeListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecipeListBinding
    private val viewModel: RecipeListViewModel by viewModels()
    private lateinit var recipeAdapter: RecipeListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()

        // Observe recipes & update UI
        viewModel.getRecipes("").observe(this) { recipes ->
            if (recipes != null) {
                recipeAdapter.setRecipes(recipes)
            }
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
