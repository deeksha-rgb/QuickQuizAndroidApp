package com.example.quickquixapp.data

import android.content.Context
import com.example.quickquixapp.data.datasource.PreferenceDataSource

/**
 * QuizPreferenceManager
 *
 * Handles ONLY app-level settings using SharedPreferences.
 */
class QuizPreferenceManager(
    context: Context
) : PreferenceDataSource {

    private val prefs =
        context.getSharedPreferences("quiz_prefs", Context.MODE_PRIVATE)

    // ---------------- DARK MODE ----------------

    override fun saveDarkMode(enabled: Boolean) {
        prefs.edit()
            .putBoolean("dark_mode", enabled)
            .apply()
    }

    override fun isDarkModeEnabled(): Boolean {
        return prefs.getBoolean("dark_mode", false)
    }
}
