package dev.divyanshgemini.cookmate.ui.recipe.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dev.divyanshgemini.cookmate.databinding.ActivityRecipeDetailBinding

class RecipeDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecipeDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recipeId = intent.getIntExtra("RECIPE_ID", 0)
    }
}