package fhnw.ws6c.theapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import fhnw.ws6c.theapp.model.Screen
import fhnw.ws6c.theapp.model.TheModel
import fhnw.ws6c.theapp.ui.TopBar
import fhnw.ws6c.theapp.ui.theme.AppTheme

@Composable
fun Recipe_Screen(model: TheModel){
    with(model){
        AppTheme(darkTheme) {
            Scaffold(
                topBar = { TopBar(model, currentDrink.name, Icons.Filled.ArrowBack, {currentScreen = Screen.CATEGORY_SCREEN}) }){
                Content(model)
            }
        }
    }
}

@Composable
fun Content(model: TheModel){
    with(model.currentDrink){
        Column() {
            Text("Id: " + id)
            Text("Number of ingredients: " + ingredients.size)
            Text("Glas: " + glass)
            Text("Ingredients: ")
            LazyColumn{
                items(ingredients.size){
                    Text(meassurements.get(it) + " " + ingredients.get(it))
                }
            }
        }
    }
}