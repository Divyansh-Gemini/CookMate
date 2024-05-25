package dev.divyanshgemini.cookmate.data.repository

import android.util.Log
import dev.divyanshgemini.cookmate.AppwriteClientManager
import io.appwrite.ID
import io.appwrite.services.Account
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AppwriteRepository {
    private val account = Account(AppwriteClientManager.getClient())

    suspend fun signup(name: String, email: String, password: String): Result<Boolean> {
        return withContext(Dispatchers.IO) {
            try {
                account.create(ID.unique(), email, password, name)
                Result.Success(true)
            } catch (e: Exception) {
                Log.i("SIGNUP", e.message.toString())
                Result.Error(e)
            }
        }
    }

    suspend fun login(email: String, password: String): Result<Boolean> {
        return withContext(Dispatchers.IO) {
            try {
                account.createEmailPasswordSession(email, password)
                Result.Success(true)
            } catch (e: Exception) {
                Log.i("LOGIN", e.message.toString())
                Result.Error(e)
            }
        }
    }

    suspend fun logout(): Result<Boolean> {
        return withContext(Dispatchers.IO) {
            try {
                account.deleteSession("current")
                Result.Success(true)
            } catch (e: Exception) {
                Result.Error(e)
            }
        }
    }
}
