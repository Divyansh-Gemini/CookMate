package dev.divyanshgemini.cookmate.data.repository

// A generic class that holds a value with its loading status.
sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
}
