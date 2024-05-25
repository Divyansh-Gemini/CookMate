package dev.divyanshgemini.cookmate.utils

import android.text.Html

class Formatters {
    companion object {
        // Convert HTML to plain text
        fun getHtml(htmlBody: String): String {
            return Html.fromHtml(htmlBody, Html.FROM_HTML_MODE_LEGACY).toString()
        }
    }
}