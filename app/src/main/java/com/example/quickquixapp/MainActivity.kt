package com.example.quickquixapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.quickquixapp.di.ViewModelFactory
import com.example.quickquixapp.ui.home.HomeViewModel
import com.example.quickquixapp.ui.navigation.QuickQuixNavGraph
import com.example.quickquixapp.ui.theme.QuickQuixAppTheme

class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            WindowCompat.setDecorFitsSystemWindows(window, true)
            val navController = rememberNavController()

            val appContainer =
                (applicationContext as QuickQuixApplication).appContainer

            val viewModelFactory = ViewModelFactory(appContainer)

            val homeViewModel: HomeViewModel =
                viewModel(factory = viewModelFactory)


            QuickQuixAppTheme(
                darkTheme = homeViewModel.isDarkMode
            ) {
                QuickQuixNavGraph(
                    navController = navController,
                    viewModelFactory = viewModelFactory,
                    homeViewModel = homeViewModel
                )
            }
        }
    }
}