package dev.divyanshgemini.cookmate.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.spoonacular.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: SpoonacularApiService by lazy {
        retrofit.create(SpoonacularApiService::class.java)
    }
}
