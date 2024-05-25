package dev.divyanshgemini.cookmate.ui.auth.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.divyanshgemini.cookmate.data.repository.AppwriteRepository
import dev.divyanshgemini.cookmate.data.repository.Result
import kotlinx.coroutines.launch

class SignupViewModel: ViewModel() {
    private val appwriteRepository = AppwriteRepository()

    private val _signupResult = MutableLiveData<Boolean>()
    val signupResult: LiveData<Boolean> get() = _signupResult

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    private val _inputValidationResult = MutableLiveData<Boolean>()
    val inputValidationResult: LiveData<Boolean> get() = _inputValidationResult

    fun signup(name: String, email: String, password: String) {
        viewModelScope.launch {
            when (val result = appwriteRepository.signup(name, email, password)) {
                is Result.Success -> _signupResult.value = result.data
                is Result.Error -> {
                    _errorMessage.value = when (result.exception.message) {
                        "Invalid `email` param: Value must be a valid email address" -> "Invalid email address"
                        "Invalid `password` param: Password must be between 8 and 265 characters long, and should not be one of the commonly used password." -> "Password should be at least 8 characters long"
                        "A user with the same id, email, or phone already exists in this project." -> "User already exists with same email ID"
                        else -> result.exception.message
                    }
                }
            }
        }
    }

    fun validateInputs(name: String, email: String, password: String): Boolean {
        return if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            _inputValidationResult.value = false
            false
        } else {
            _inputValidationResult.value = true
            true
        }
    }
}