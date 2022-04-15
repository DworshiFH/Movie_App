package com.example.kinoxto2.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.kinoxto2.screens.detail.DetailScreen
import com.example.kinoxto2.screens.favourites.FavouritesScreen
import com.example.kinoxto2.screens.home.HomeScreen
import com.example.kinoxto2.viewmodels.FavouritesViewModel

@Composable
fun MovieNavigation(){
    val navController = rememberNavController()

    val favViewModel: FavouritesViewModel = viewModel()

    //favViewModel.addFav(getMovies()[1])

    NavHost(navController = navController, startDestination = MovieScreens.HomeScreen.name){
        composable(
            route = MovieScreens.HomeScreen.name
        ) {
            HomeScreen(navController, favViewModel)
        }

        composable(
            route= MovieScreens.DetailScreen.name + "/{movieId}",
            arguments = listOf(navArgument("movieId"){
                type = NavType.StringType
            })
        ) {
                backStackEntry -> DetailScreen(navController = navController,
                                                movieId = backStackEntry.arguments?.getString("movieId"),
                                                viewModel = favViewModel)
        }

        composable(
            route= MovieScreens.FavouritesScreen.name
        ) {
                FavouritesScreen(navController = navController, viewModel = favViewModel)
        }
    }
}