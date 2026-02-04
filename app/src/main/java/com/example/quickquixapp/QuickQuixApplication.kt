package com.example.quickquixapp

import android.app.Application
import android.app.NotificationManager
import com.clevertap.android.sdk.ActivityLifecycleCallback
import com.clevertap.android.sdk.CleverTapAPI
import com.example.quickquixapp.di.AppContainer

class QuickQuixApplication : Application() {

    lateinit var appContainer: AppContainer
        private set

    override fun onCreate() {

        super.onCreate()

        CleverTapAPI.setDebugLevel(CleverTapAPI.LogLevel.VERBOSE)
        ActivityLifecycleCallback.register(this)
        CleverTapAPI.createNotificationChannel(
            applicationContext,
            "quiz_channel",                // Channel ID (must match dashboard)
            "Quiz Notifications",          // Channel Name
            "Notifications related to quizzes",
            NotificationManager.IMPORTANCE_HIGH,
            true                           // Show badge
        )


        //  THIS is where Context comes from
        appContainer   = AppContainer(this)
    }
}

//appContainer -> variable
//:AppContainer -> Type
//AppContainer(this) -> object