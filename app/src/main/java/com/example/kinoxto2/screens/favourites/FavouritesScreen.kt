package com.example.kinoxto2.screens.favourites

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.kinoxto2.navigation.MovieScreens
import com.example.kinoxto2.viewmodels.FavouritesViewModel
import com.example.kinoxto2.widgets.MovieRow

@Composable
fun FavouritesScreen(navController: NavController = rememberNavController(), viewModel: FavouritesViewModel){

    val favs = viewModel.getFavs()

    //DEBUG: print movie titles to console
    for(movie in favs){
        Log.i("FavouritesScreen", movie.title)
    }

    Scaffold(
        topBar = {
            TopAppBar{
                Row(verticalAlignment = Alignment.CenterVertically){
                    Spacer(modifier = Modifier.width(10.dp))
                    Icon(imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Arrow Back",
                        modifier = Modifier.clickable {
                            navController.popBackStack()
                        })
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = "My Favourite Movies", style = MaterialTheme.typography.h6)
                }
            }
        }) {
        LazyColumn {
            items(favs) { movie ->
                MovieRow(
                    movie = movie,
                    onItemClick = {
                            movieId -> navController.navigate(route = MovieScreens.DetailScreen.name + "/$movieId") }
                ) {
                    //no content to be injected
                }
            }
        }
    }
}

