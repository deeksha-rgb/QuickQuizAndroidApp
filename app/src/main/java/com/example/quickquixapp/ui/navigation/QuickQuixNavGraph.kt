package com.example.quickquixapp.ui.navigation



import androidx.compose.runtime.Composable
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
    navController: NavHostController,
    homeViewModel: HomeViewModel,
    quizViewModel: QuizViewModel,
    highScoreViewModel: HighScoreViewModel,
    enterNameViewModel: EnterNameViewModel
) {

    NavHost(
        navController = navController,
        startDestination = NavRoute.Home.route
    ) {

        composable(NavRoute.Home.route) {
            HomeScreen(
                viewModel = homeViewModel,
                onStartClick = {
                    navController.navigate(NavRoute.EnterName.route)
                }
            )
        }

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
            HighScoreScreen(viewModel = highScoreViewModel)
        }
    }
}