package dev.divyanshgemini.cookmate.ui

import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import dev.divyanshgemini.cookmate.R
import dev.divyanshgemini.cookmate.ui.auth.login.LoginActivity
import dev.divyanshgemini.cookmate.ui.recipe.detail.RecipeDetailActivity
import dev.divyanshgemini.cookmate.ui.recipe.list.RecipeListActivity
import dev.divyanshgemini.cookmate.ui.splash.SplashActivity

class MainActivity : AppCompatActivity() {
    private lateinit var pref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            // Splash screen for Android 12 and above
            installSplashScreen()

            // Check if the user is already logged in
            pref = getSharedPreferences("CookMate", MODE_PRIVATE)
            val isLoggedIn = pref.getBoolean("isLoggedIn", false)

            // Navigate to the appropriate screen after a delay
            if (isLoggedIn)
                startActivity(Intent(this@MainActivity, RecipeListActivity::class.java))
            else
                startActivity(Intent(this@MainActivity, LoginActivity::class.java))
            finish()
        } else {
            // Splash screen for Android 11 and below
            startActivity(Intent(this, SplashActivity::class.java))
            finish()
        }

        setContentView(R.layout.activity_main)
    }
}