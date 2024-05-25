package dev.divyanshgemini.cookmate.ui.auth.login

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.divyanshgemini.cookmate.data.repository.AppwriteRepository
import dev.divyanshgemini.cookmate.data.repository.Result
import kotlinx.coroutines.launch

class LoginViewModel(application: Application): AndroidViewModel(application) {
    private val appwriteRepository = AppwriteRepository()

    private val pref: SharedPreferences = getApplication<Application>().getSharedPreferences("CookMate", Context.MODE_PRIVATE)
    private val editor = pref.edit()

    private val _loginResult = MutableLiveData<Boolean>()
    val loginResult: LiveData<Boolean> get() = _loginResult

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    private val _inputValidationResult = MutableLiveData<Boolean>()
    val inputValidationResult: LiveData<Boolean> get() = _inputValidationResult

    fun login(email: String, password: String) {
        viewModelScope.launch {
            when (val result = appwriteRepository.login(email, password)) {
                is Result.Success -> {
                    _loginResult.value = result.data
                    editor.putBoolean("isLoggedIn", true)
                    editor.commit()
                }
                is Result.Error -> {
                    _errorMessage.value = when(result.exception.message) {
                        "Creation of a session is prohibited when a session is active." -> "User already logged in"
                        "Invalid `email` param: Value must be a valid email address" -> "Invalid email"
                        "Invalid `password` param: Password must be between 8 and 256 characters long." -> "Invalid password"
                        "Invalid credentials. Please check the email and password." -> "Invalid credentials"
                        else -> result.exception.message
                    }
                }
            }
        }
    }

    fun validateInputs(email: String, password: String): Boolean {
        return if (email.isEmpty() || password.isEmpty()) {
            _inputValidationResult.value = false
            false
        } else {
            _inputValidationResult.value = true
            true
        }
    }
}