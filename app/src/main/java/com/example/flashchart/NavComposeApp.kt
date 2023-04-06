package com.example.flashchart

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.example.flashchart.nav.Action
import com.example.flashchart.nav.Destination.AuthenticationOption
import com.example.flashchart.nav.Destination.Home
import com.example.flashchart.nav.Destination.Login
import com.example.flashchart.nav.Destination.Register
import com.example.flashchart.ui.theme.FlashChartTheme
import com.example.flashchart.view.AuthenticationView
import com.example.flashchart.view.home.HomeView
import com.example.flashchart.view.login.LoginViewModel
import com.example.flashchart.view.login.LoginView
import com.example.flashchart.view.register.RegisterView

/**
 * The main Navigation composable which will handle all the navigation stack.
 */

@Composable
fun NavComposeApp() {
    val navController = rememberNavController()
    val actions = remember(navController) { Action(navController) }
    FlashChartTheme {
        NavHost(
            navController = navController,
            startDestination =
            if (FirebaseAuth.getInstance().currentUser != null)
                Home
            else
                AuthenticationOption
        ) {
            composable(AuthenticationOption) {
                AuthenticationView(
                    register = actions.register,
                    login = actions.login
                )
            }
            composable(Register) {
                RegisterView(
                    home = actions.home,
                    back = actions.navigateBack
                )
            }
            composable(Login) {
                LoginView(
                    home = actions.home,
                    back = actions.navigateBack
                )
            }
            composable(Home) {
                HomeView()
            }
        }
    }
}