package dev.divyanshgemini.cookmate.data.model

import com.google.gson.annotations.SerializedName

data class RecipeResponse(
    val offset: Int,

    val number: Int,

    @SerializedName("results")
    val recipes: List<Recipe>,

    val totalResults: Int
)
