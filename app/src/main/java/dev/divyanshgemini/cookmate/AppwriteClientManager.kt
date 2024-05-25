package dev.divyanshgemini.cookmate

import android.content.Context
import io.appwrite.Client

object AppwriteClientManager {
    private var client: Client? = null

    fun init(context: Context) {
        client = Client(context)
            .setEndpoint("https://cloud.appwrite.io/v1")
            .setProject("cookmate")
    }

    fun getClient(): Client {
        return client ?: throw IllegalStateException("Appwrite client not initialized")
    }
}