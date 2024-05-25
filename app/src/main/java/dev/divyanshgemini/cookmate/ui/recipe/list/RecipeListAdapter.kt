package dev.divyanshgemini.cookmate.ui.recipe.list

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.divyanshgemini.cookmate.R
import dev.divyanshgemini.cookmate.data.model.Recipe
import dev.divyanshgemini.cookmate.databinding.RecipeItemBinding
import dev.divyanshgemini.cookmate.ui.recipe.detail.RecipeDetailActivity

class RecipeListAdapter : RecyclerView.Adapter<RecipeListAdapter.RecipeViewHolder>() {
    private val recipes = mutableListOf<Recipe>()

    @SuppressLint("NotifyDataSetChanged")
    fun setRecipes(recipes: List<Recipe>) {
        Log.i("TAG", "RecipeListAdapter :: setRecipes")
        this.recipes.clear()
        this.recipes.addAll(recipes)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        Log.i("TAG", "RecipeListAdapter :: onCreateViewHolder")
        val binding = RecipeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        Log.i("TAG", "RecipeListAdapter :: onBindViewHolder")
        holder.bind(recipes[position])
    }

    override fun getItemCount(): Int {
        return recipes.size
    }

    inner class RecipeViewHolder(private val binding: RecipeItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(recipe: Recipe) {
            Log.i("TAG", "RecipeViewHolder :: bind")
            binding.recipeTitle.text = recipe.title
            Glide.with(binding.root.context)
                .load(recipe.image)
                .placeholder(R.drawable.food)
                .into(binding.recipeImage)

            binding.root.setOnClickListener {
                val intent = Intent(it.context, RecipeDetailActivity::class.java)
                intent.putExtra("RECIPE_ID", recipe.id)
                it.context.startActivity(intent)
            }
        }
    }
}
