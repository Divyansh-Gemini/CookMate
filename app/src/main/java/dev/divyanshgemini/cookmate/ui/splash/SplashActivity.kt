package dev.divyanshgemini.cookmate.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import dev.divyanshgemini.cookmate.databinding.ActivitySplashBinding
import dev.divyanshgemini.cookmate.ui.auth.login.LoginActivity
import dev.divyanshgemini.cookmate.ui.recipe.list.RecipeListActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    private lateinit var pref: SharedPreferences
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Check if the user is already logged in
        pref = getSharedPreferences("CookMate", MODE_PRIVATE)
        val isLoggedIn = pref.getBoolean("isLoggedIn", false)

        // Navigate to the appropriate screen after a delay
        Handler(Looper.getMainLooper()).postDelayed({
            if (isLoggedIn)
                startActivity(Intent(this@SplashActivity, RecipeListActivity::class.java))
            else
                startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
            finish()
        }, 2000)
    }
}
