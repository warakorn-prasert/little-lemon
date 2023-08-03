package com.example.littlelemon

import android.content.Context
import android.preference.PreferenceManager
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument


@Composable
fun Navigation(
    context: Context,
    navController: NavHostController,
    menuItems: List<MenuItemRoom>
) {
    val sharedPreferences = context.getSharedPreferences("LittleLemon.prefs", Context.MODE_PRIVATE)
    var isLogin = sharedPreferences.getBoolean("isLogin", false)
    var startDestination = if(isLogin) Home.route else Onboarding.route

    NavHost(navController = navController, startDestination = startDestination) {
        composable(Onboarding.route) {
            Onboarding(context, navController)
        }
        composable(Home.route) {
            Home(menuItems)
        }
        composable(Profile.route) {
            Profile(context, navController)
        }
    }
}