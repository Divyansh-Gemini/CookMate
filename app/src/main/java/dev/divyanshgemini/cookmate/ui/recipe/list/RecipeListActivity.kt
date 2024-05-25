package dev.divyanshgemini.cookmate.ui.recipe.list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dev.divyanshgemini.cookmate.R
import dev.divyanshgemini.cookmate.databinding.ActivityLoginBinding
import dev.divyanshgemini.cookmate.databinding.ActivityRecipeListBinding

class RecipeListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecipeListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeListBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}