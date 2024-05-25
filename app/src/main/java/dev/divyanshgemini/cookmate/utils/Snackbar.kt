package dev.divyanshgemini.cookmate.utils

import android.content.Context
import android.view.View
import com.google.android.material.snackbar.Snackbar
import dev.divyanshgemini.cookmate.R

object Snackbar {
    fun success(view: View, context: Context, message: String): Snackbar {
        return Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
            .setBackgroundTint(context.resources.getColor(R.color.green, null))
            .setTextColor(context.resources.getColor(R.color.white, null))
    }

    fun error(view: View, context: Context, message: String): Snackbar {
        return Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
            .setBackgroundTint(context.resources.getColor(R.color.red, null))
            .setTextColor(context.resources.getColor(R.color.white, null))
    }
}