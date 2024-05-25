package dev.divyanshgemini.cookmate.ui.recipe.detail

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import dev.divyanshgemini.cookmate.databinding.ActivityRecipeDetailBinding
import dev.divyanshgemini.cookmate.utils.Formatters

class RecipeDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecipeDetailBinding
    private val viewModel: RecipeDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get recipe id from the intent
        val recipeId = intent.getIntExtra("RECIPE_ID", 0)

        // Observe recipe information & update UI
        viewModel.getRecipeInformation(recipeId).observe(this) { recipeInformation ->
            if (recipeInformation != null) {
                Glide.with(this)
                    .load(recipeInformation.image)
                    .into(binding.recipeImage)

                binding.recipeTitle.text = recipeInformation.title
                binding.recipeDescription.text = Formatters.getHtml(recipeInformation.summary)
                binding.readyInMinutes.text = "Ready in ${recipeInformation.readyInMinutes} minutes"
                binding.servings.text = "Servings: ${recipeInformation.servings}"
            }
        }
    }
}