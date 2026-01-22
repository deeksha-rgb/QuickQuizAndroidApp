package com.example.quickquixapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.quickquixapp.di.ViewModelFactory
import com.example.quickquixapp.ui.highscore.HighScoreScreen
import com.example.quickquixapp.ui.highscore.HighScoreViewModel
import com.example.quickquixapp.ui.home.HomeScreen
import com.example.quickquixapp.ui.home.HomeViewModel
import com.example.quickquixapp.ui.navigation.NavRoute
import com.example.quickquixapp.ui.quiz.QuizScreen
import com.example.quickquixapp.ui.quiz.QuizViewModel
import com.example.quickquixapp.ui.quiz.QuizViewModel.QuizUiState
import com.example.quickquixapp.ui.result.ResultScreen
import com.example.quickquixapp.ui.theme.QuickQuixAppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            val navController = rememberNavController()

            val appContainer =
                (applicationContext as QuickQuixApplication).appContainer
            //this provides the resources to all the files

            val factory = ViewModelFactory(appContainer)
            //this provies respository to the viewmodels

            val homeViewModel: HomeViewModel = viewModel(factory = factory)
            val quizViewModel: QuizViewModel = viewModel(factory = factory)
            val highScoreViewModel: HighScoreViewModel = viewModel(factory = factory)

            //  SEALED STATE BASED NAVIGATION
            LaunchedEffect(quizViewModel.quizState) {
                if (quizViewModel.quizState is QuizUiState.Finished) {
                    navController.navigate(NavRoute.Result.route) {
                        popUpTo(NavRoute.Home.route) { inclusive = false }
                    }
                }
            }

            QuickQuixAppTheme(darkTheme = homeViewModel.isDarkMode) {

                NavHost(
                    navController = navController,
                    startDestination = NavRoute.Home.route
                ) {

                    composable(NavRoute.Home.route) {
                        HomeScreen(
                            viewModel = homeViewModel,
                            onStartClick = {
                                quizViewModel.userName = homeViewModel.userName
                                quizViewModel.startQuiz()
                                navController.navigate(NavRoute.Quiz.route)
                            }
                        )
                    }

                    composable(NavRoute.Quiz.route) {
                        QuizScreen(viewModel = quizViewModel)
                    }

                    composable(NavRoute.Result.route) {
                        ResultScreen(
                            score = quizViewModel.score,
                            totalQuestions = quizViewModel.totalQuestions(),
                            highScore = highScoreViewModel.highScore,
                            onRestart = {
                                quizViewModel.restartQuiz()
                                navController.popBackStack(
                                    NavRoute.Home.route,
                                    inclusive = false
                                )
                            },
                            onViewHighScores = {
                                navController.navigate(NavRoute.HighScore.route)
                            }
                        )
                    }


                    composable(NavRoute.HighScore.route) {
                        HighScoreScreen(
                            viewModel = highScoreViewModel,
                            onBack = { navController.popBackStack() }
                        )
                    }
                }
            }
        }
    }
}
