package com.mnewland.giftmanager.com.mnewland.giftmanager.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.mnewland.giftmanager.com.mnewland.giftmanager.ui.home.HomeDestination
import com.mnewland.giftmanager.com.mnewland.giftmanager.ui.home.ListLayout
import com.mnewland.giftmanager.com.mnewland.giftmanager.ui.add_new_person.AddPersonDestination
import com.mnewland.giftmanager.com.mnewland.giftmanager.ui.add_new_person.AddNewPersonPage
import com.mnewland.giftmanager.com.mnewland.giftmanager.ui.add_new_person.ProfileDestination
import com.mnewland.giftmanager.com.mnewland.giftmanager.ui.add_new_person.ProfilePage
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
                        AddPersonDestination.route
                    )
                },
                navigateToProfile = { navController.navigate("${ProfileDestination.route}/$it") }
            )
        }
        composable(route = SettingsDestination.route){
            SettingsScreen(
               onBackButtonPressed = {navController.navigateUp()}
            )
        }
        composable(
            route = AddPersonDestination.route
        ) {
            AddNewPersonPage(
                onBackButtonClick = {navController.navigateUp()},
                onSettingsButtonClick = {navController.navigate(SettingsDestination.route)},
                navigateToAddedPerson = { navController.navigate("${ProfileDestination.route}/$it") }
            )
        }
        composable(
            route = ProfileDestination.routeWithArgs,
            arguments = listOf(navArgument(ProfileDestination.personIdArg) {
                type = NavType.IntType
            })
        ) {
            ProfilePage(
                onBackButtonClick = {navController.navigate(HomeDestination.route)},
                onSettingsButtonClick = {navController.navigate(SettingsDestination.route)}
            )
        }
    }
}