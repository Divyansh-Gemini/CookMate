package dev.divyanshgemini.cookmate

import android.app.Application

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        AppwriteClientManager.init(this)
    }
}
