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
import com.example.quickquixapp.ui.name.EnterNameScreen
import com.example.quickquixapp.ui.name.EnterNameViewModel
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

            val factory = ViewModelFactory(appContainer)

            val homeViewModel: HomeViewModel = viewModel(factory = factory)
            val quizViewModel: QuizViewModel = viewModel(factory = factory)
            val highScoreViewModel: HighScoreViewModel = viewModel(factory = factory)
            val enterNameViewModel: EnterNameViewModel = viewModel(factory = factory)

            // 🔹 Auto navigate to Result when quiz finishes
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
                    startDestination = NavRoute.Home.route   //  FIXED
                ) {

                    //  HOME
                    composable(NavRoute.Home.route) {
                        HomeScreen(
                            viewModel = homeViewModel,
                            onStartClick = {
                                navController.navigate(NavRoute.EnterName.route)
                            }
                        )
                    }

                    //  ENTER NAME
                    composable(NavRoute.EnterName.route) {
                        EnterNameScreen(
                            viewModel = enterNameViewModel,
                            onContinue = { name ->
                                quizViewModel.startQuiz(
                                    userName = name,
                                    difficulty = homeViewModel.selectedDifficulty
                                )
                                navController.navigate(NavRoute.Quiz.route)
                            }
                        )
                    }

                    //  QUIZ
                    composable(NavRoute.Quiz.route) {
                        QuizScreen(viewModel = quizViewModel)
                    }

                    //  RESULT
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

                    // HIGH SCORE
                    composable(NavRoute.HighScore.route) {
                        HighScoreScreen(viewModel = highScoreViewModel)
                    }
                }
            }
        }
    }
}