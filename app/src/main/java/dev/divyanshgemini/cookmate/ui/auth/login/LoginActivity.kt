package dev.divyanshgemini.cookmate.ui.auth.login

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import dev.divyanshgemini.cookmate.databinding.ActivityLoginBinding
import dev.divyanshgemini.cookmate.ui.auth.signup.SignupActivity
import dev.divyanshgemini.cookmate.ui.recipe.list.RecipeListActivity
import dev.divyanshgemini.cookmate.utils.Snackbar

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.navigationText.setOnClickListener {
            startActivity(Intent(this@LoginActivity, SignupActivity::class.java))
        }

        binding.loginBtn.setOnClickListener {
            val email = binding.editTextEmail.text.toString().trim()
            val password = binding.editTextPassword.text.toString().trim()

            if (loginViewModel.validateInputs(email, password)) {
                loginViewModel.login(email, password)
            } else {
                // Set error to TextInputLayout if any field is empty
                if (email.isEmpty()) {
                    binding.textInputLayoutEmail.error = "Email cannot be empty"
                } else {
                    binding.textInputLayoutEmail.error = null
                }
                if (password.isEmpty()) {
                    binding.textInputLayoutPassword.error = "Password cannot be empty"
                } else {
                    binding.textInputLayoutPassword.error = null
                }
            }
        }

        // Add onFocusChangeListener to clear error when input box is touched
        setOnTouchListener(binding.editTextEmail, binding.textInputLayoutEmail)
        setOnTouchListener(binding.editTextPassword, binding.textInputLayoutPassword)

        loginViewModel.loginResult.observe(this) { result ->
            if (result) {
                Snackbar.success(binding.root, applicationContext, "Login successful").show()
                startActivity(Intent(this@LoginActivity, RecipeListActivity::class.java))
            } else {
                Snackbar.error(binding.root, applicationContext, "Login failed").show()
            }
        }

        loginViewModel.errorMessage.observe(this) { message ->
            message?.let {
                Snackbar.error(binding.root, applicationContext, it).show()
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setOnTouchListener(editText: EditText, textInputLayout: TextInputLayout) {
        editText.setOnTouchListener { _, event ->
            // Clear error when input box is touched
            if (event.action == MotionEvent.ACTION_UP)
                textInputLayout.error = null
            false
        }
    }
}