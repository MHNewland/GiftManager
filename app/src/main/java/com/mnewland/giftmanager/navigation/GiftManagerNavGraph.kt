package com.mnewland.giftmanager.com.mnewland.giftmanager.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.mnewland.giftmanager.ui.HomeDestination
import com.mnewland.giftmanager.ui.ListLayout
import com.mnewland.giftmanager.ui.ProfileDestination
import com.mnewland.giftmanager.ui.ProfilePage
import com.mnewland.giftmanager.ui.SettingsDestination
import com.mnewland.giftmanager.ui.SettingsScreen

@Composable
fun GiftManagerNavGraph (
    navController: NavHostController,
    modifier: Modifier = Modifier,

){
    NavHost(
        navController = navController,
        startDestination = HomeDestination.route,
    ) {
        composable(route = HomeDestination.route) {
            ListLayout(
                onSettingsButtonPressed = {navController.navigate(SettingsDestination.route)},
                onAddPersonPressed = {
                    navController.navigate(
                        ProfileDestination.route
                    )
                }
            )
        }
        composable(route = SettingsDestination.route){
            SettingsScreen(
               onBackButtonPressed = {navController.navigateUp()}
            )
        }
        composable(
            route = ProfileDestination.route//routeWithArgs,
//            arguments = listOf(navArgument(ProfileDestination.personIdArg) {
//                type = NavType.IntType
//            })
        ) {
            ProfilePage(
                onBackButtonClick = {navController.navigateUp()},
                onSettingsButtonClick = {navController.navigate(SettingsDestination.route)}
            )
        }
    }
}