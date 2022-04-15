package com.example.kinoxto2.screens.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.kinoxto2.navigation.MovieScreens
import com.example.kinoxto2.viewmodels.FavouritesViewModel
import com.example.kinoxto2.widgets.FavouritesIcon
import com.example.kinoxto2.widgets.MovieRow
import com.example.kinoxto2.widgets.TopAppBarDropdownMenu
import com.example.testapp.models.Movie
import com.example.testapp.models.getMovies

@Preview(showBackground = true)
@Composable
fun HomeScreen(navController: NavController = rememberNavController(), viewModel: FavouritesViewModel = viewModel()){
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Movies")
                }, actions = {
                    TopAppBarDropdownMenu(navController = navController)
                })
        }) {
        MainContent(navController = navController, viewModel = viewModel)
    }
}

@Composable
fun MainContent(navController: NavController,
                viewModel: FavouritesViewModel = viewModel(),
                movieList: List<Movie> = getMovies()){
    LazyColumn {
        items(movieList) { movie ->
            MovieRow(
                movie = movie,
                onItemClick = { movieId ->
                    navController.navigate(route = MovieScreens.DetailScreen.name + "/$movieId") }
            ) {
                //injecting content: "Add to Favourites Button"
                FavouritesIcon(isFav = viewModel.isFavourite(movie), movie = movie){
                    m->
                    if(viewModel.isFavourite(m)){
                        viewModel.removeFav(m)
                    } else {
                        viewModel.addFav(m)
                    }
                }
            }
        }
    }
}