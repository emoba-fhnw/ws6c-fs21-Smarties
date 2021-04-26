package fhnw.ws6c.theapp.ui.screens

import androidx.compose.foundation.layout.Column
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
    with(model){
        Column() {
            Text("Id: " + currentDrink.id)
            Text("Number of ingredients: " + currentDrink.ingredients.size)
            Text("Glas: " + currentDrink.glass)
        }
    }
}