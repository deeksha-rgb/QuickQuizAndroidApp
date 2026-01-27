package com.example.quickquixapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.quickquixapp.ui.highscore.HighScoreScreen
import com.example.quickquixapp.ui.highscore.HighScoreViewModel
import com.example.quickquixapp.ui.home.HomeScreen
import com.example.quickquixapp.ui.home.HomeViewModel
import com.example.quickquixapp.ui.name.EnterNameScreen
import com.example.quickquixapp.ui.name.EnterNameViewModel
import com.example.quickquixapp.ui.quiz.QuizScreen
import com.example.quickquixapp.ui.quiz.QuizViewModel
import com.example.quickquixapp.ui.result.ResultScreen

@Composable
fun QuickQuixNavGraph(
    navController: NavHostController
) {

    NavHost(
        navController = navController,
        startDestination = NavRoute.Home.route
    ) {

        composable(NavRoute.Home.route) {
            val homeViewModel: HomeViewModel = viewModel()

            HomeScreen(
                viewModel = homeViewModel,
                onStartClick = {
                    navController.navigate(NavRoute.EnterName.route)
                }
            )
        }

        composable(NavRoute.EnterName.route) {
            val enterNameViewModel: EnterNameViewModel = viewModel()
            val homeViewModel: HomeViewModel =
                viewModel(navController.getBackStackEntry(NavRoute.Home.route))

            EnterNameScreen(
                viewModel = enterNameViewModel,
                onContinue = {
                    navController.navigate(NavRoute.Quiz.route)
                }
            )
        }

        composable(NavRoute.Quiz.route) {
            val quizViewModel: QuizViewModel = viewModel()

            QuizScreen(viewModel = quizViewModel)
        }

        composable(NavRoute.Result.route) {
            val quizViewModel: QuizViewModel =
                viewModel(navController.getBackStackEntry(NavRoute.Quiz.route))
            val highScoreViewModel: HighScoreViewModel = viewModel()

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
            val highScoreViewModel: HighScoreViewModel = viewModel()
            HighScoreScreen(viewModel = highScoreViewModel)
        }
    }
}