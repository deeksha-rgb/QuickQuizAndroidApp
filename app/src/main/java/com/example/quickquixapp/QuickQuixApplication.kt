package com.example.quickquixapp

import android.app.Application
import com.clevertap.android.sdk.CleverTapAPI
import com.example.quickquixapp.di.AppContainer

class QuickQuixApplication : Application() {

    lateinit var appContainer: AppContainer
        private set

    override fun onCreate() {
        super.onCreate()

        CleverTapAPI.setDebugLevel(CleverTapAPI.LogLevel.VERBOSE)


        //  THIS is where Context comes from
        appContainer   = AppContainer(this)
    }
}

//appContainer -> variable
//:AppContainer -> Type
//AppContainer(this) -> object