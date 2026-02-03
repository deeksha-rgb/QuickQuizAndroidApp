package com.example.quickquixapp.analytics

import android.content.Context
import android.util.Log
import com.clevertap.android.sdk.CleverTapAPI

class CleverTapAnalyticsTracker(
    context: Context
) : AnalyticsTracker {

    private val cleverTap =
        CleverTapAPI.getDefaultInstance(context)

    override fun trackEvent(
        name: String,
        properties: Map<String, Any>
    ) {
        Log.v("CT_TRACKER", "About to fire Quiz_Completed")
        cleverTap?.pushEvent(name, properties)
        Log.v("CT_TRACKER", "Quiz_Completed fired")
    }
}
