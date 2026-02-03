package com.example.quickquixapp.analytics

interface AnalyticsTracker {
        fun trackEvent(
            name: String,
            properties: Map<String, Any>
        )
    }
