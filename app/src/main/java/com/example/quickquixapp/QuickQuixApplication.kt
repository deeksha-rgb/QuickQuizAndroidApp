package com.example.quickquixapp

import android.app.Application
import com.example.quickquixapp.di.AppContainer

class QuickQuixApplication : Application() {

    lateinit var appContainer: AppContainer
        private set

    override fun onCreate() {
        super.onCreate()

        //  THIS is where Context comes from
        appContainer   = AppContainer(this)
    }
}

//appContainer -> variable
//:AppContainer -> Type
//AppContainer(this) -> object