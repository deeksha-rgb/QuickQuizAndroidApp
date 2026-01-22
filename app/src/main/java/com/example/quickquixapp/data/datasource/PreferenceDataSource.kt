package com.example.quickquixapp.data.datasource

/**
 * PreferenceDataSource
 *
 * This interface defines ONLY app-level preferences.
 * High scores are now handled by Room Database.
 */
interface PreferenceDataSource {

    /**
     * saveDarkMode: Saves whether dark mode is enabled or not.
     */
    fun saveDarkMode(enabled: Boolean)

    /**
     * isDarkModeEnabled: Returns true if dark mode is enabled.
     */
    fun isDarkModeEnabled(): Boolean
}