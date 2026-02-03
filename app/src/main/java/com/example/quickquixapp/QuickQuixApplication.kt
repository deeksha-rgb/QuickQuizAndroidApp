package com.example.quickquixapp

import android.app.Application
import com.clevertap.android.sdk.ActivityLifecycleCallback
import com.clevertap.android.sdk.CleverTapAPI
import com.example.quickquixapp.di.AppContainer

class QuickQuixApplication : Application() {

    lateinit var appContainer: AppContainer
        private set

    override fun onCreate() {
        CleverTapAPI.setDebugLevel(CleverTapAPI.LogLevel.VERBOSE)
        ActivityLifecycleCallback.register(this)
        super.onCreate()

        CleverTapAPI.getDefaultInstance(applicationContext)
        //  THIS is where Context comes from
        appContainer   = AppContainer(this)
    }
}

//appContainer -> variable
//:AppContainer -> Type
//AppContainer(this) -> object