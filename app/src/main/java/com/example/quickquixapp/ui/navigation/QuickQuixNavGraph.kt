package com.example.quickquixapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.quickquixapp.domain.model.Difficulty
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
    viewModelFactory: ViewModelProvider.Factory,
    homeViewModel: HomeViewModel
) {

    NavHost(
        navController = navController,
        startDestination = NavRoute.Home.route
    ) {

        // ---------------- HOME ----------------
        composable(NavRoute.Home.route) {
            HomeScreen(
                viewModel = homeViewModel, // 👈 SAME ViewModel from MainActivity
                onStartClick = {
                    navController.navigate(NavRoute.EnterName.route)
                }
            )
        }

        // ---------------- ENTER NAME ----------------
        composable(NavRoute.EnterName.route) {
            val enterVM: EnterNameViewModel =
                viewModel(factory = viewModelFactory)

            val homeVM: HomeViewModel =
                viewModel(
                    navController.getBackStackEntry(NavRoute.Home.route),
                    factory = viewModelFactory
                )

            EnterNameScreen(
                viewModel = enterVM,
                onContinue = { name ->
                    navController.navigate(
                        NavRoute.Quiz.createRoute(
                            userName = name,
                            difficulty = homeViewModel.selectedDifficulty.name
                        )
                    )
                }
            )
        }

        // ---------------- QUIZ ----------------
        composable(
            route = NavRoute.Quiz.route,
            arguments = listOf(
                navArgument("userName") { type = NavType.StringType },
                navArgument("difficulty") { type = NavType.StringType }
            )
        ) { backStackEntry ->

            val quizVM: QuizViewModel =
                viewModel(factory = viewModelFactory)

            val userName =
                backStackEntry.arguments?.getString("userName") ?: ""

            val difficulty =
                Difficulty.valueOf(
                    backStackEntry.arguments?.getString("difficulty") ?: "EASY"
                )

            //  Start quiz only once
            LaunchedEffect(Unit) {
                quizVM.startQuiz(userName, difficulty)
            }

            QuizScreen(
                viewModel = quizVM,
                onQuizFinished = {
                    navController.navigate(
                        NavRoute.Result.createRoute(
                            score = quizVM.score,
                            total = quizVM.totalQuestions()
                        )
                    ) {
                        popUpTo(NavRoute.Quiz.route) { inclusive = true }
                    }
                }
            )
        }

        // ---------------- RESULT ----------------
        composable(
            route = NavRoute.Result.route,
            arguments = listOf(
                navArgument("score") { type = NavType.IntType },
                navArgument("total") { type = NavType.IntType }
            )
        ) { backStackEntry ->

            val score =
                backStackEntry.arguments?.getInt("score") ?: 0

            val total =
                backStackEntry.arguments?.getInt("total") ?: 0

            val highScoreVM: HighScoreViewModel =
                viewModel(factory = viewModelFactory)

            ResultScreen(
                score = score,
                totalQuestions = total,
                highScore = highScoreVM.highScore,
                onRestart = {
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

        // ---------------- HIGH SCORE ----------------
        composable(NavRoute.HighScore.route) {
            val vm: HighScoreViewModel =
                viewModel(factory = viewModelFactory)

            HighScoreScreen(viewModel = vm)
        }
    }
}