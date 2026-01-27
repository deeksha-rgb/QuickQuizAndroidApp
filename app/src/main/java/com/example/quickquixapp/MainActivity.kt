package com.example.quickquixapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.quickquixapp.di.ViewModelFactory
import com.example.quickquixapp.ui.highscore.HighScoreViewModel
import com.example.quickquixapp.ui.home.HomeViewModel
import com.example.quickquixapp.ui.name.EnterNameViewModel
import com.example.quickquixapp.ui.navigation.QuickQuixNavGraph
import com.example.quickquixapp.ui.quiz.QuizViewModel
import com.example.quickquixapp.ui.theme.QuickQuixAppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()

            val appContainer =
                (applicationContext as QuickQuixApplication).appContainer

            val factory = ViewModelFactory(appContainer)

            QuickQuixAppTheme {
                QuickQuixNavGraph(
                    navController = navController,
                    viewModelFactory = factory
                )
            }
        }
    }
}