package dev.divyanshgemini.cookmate.ui.auth.signup

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import dev.divyanshgemini.cookmate.databinding.ActivitySignupBinding
import dev.divyanshgemini.cookmate.ui.auth.login.LoginActivity
import dev.divyanshgemini.cookmate.utils.Snackbar

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private val signupViewModel: SignupViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.navigationText.setOnClickListener {
            startActivity(Intent(this@SignupActivity, LoginActivity::class.java))
        }

        binding.signupBtn.setOnClickListener {
            val name = binding.editTextName.text.toString().trim()
            val email = binding.editTextEmail.text.toString().trim()
            val password = binding.editTextPassword.text.toString().trim()

            if (signupViewModel.validateInputs(name, email, password)) {
                signupViewModel.signup(name, email, password)
            } else {
                // Set error to TextInputLayout if any field is empty
                if (name.isEmpty()) {
                    binding.textInputLayoutName.error = "Name cannot be empty"
                } else {
                    binding.textInputLayoutName.error = null
                }
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
        setOnTouchListener(binding.editTextName, binding.textInputLayoutName)
        setOnTouchListener(binding.editTextEmail, binding.textInputLayoutEmail)
        setOnTouchListener(binding.editTextPassword, binding.textInputLayoutPassword)

        signupViewModel.signupResult.observe(this) { result ->
            if (result) {
                Snackbar.success(binding.root, applicationContext, "Signup successful").show()
                startActivity(Intent(this@SignupActivity, LoginActivity::class.java))
            } else {
                Snackbar.error(binding.root, applicationContext, "Signup failed").show()
            }
        }

        signupViewModel.errorMessage.observe(this) { message ->
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