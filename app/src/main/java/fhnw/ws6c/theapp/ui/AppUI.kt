package fhnw.ws6c.theapp.ui

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import fhnw.ws6c.theapp.model.Screen
import fhnw.ws6c.theapp.model.TheModel
import fhnw.ws6c.theapp.ui.screens.Recipe_Screen

@ExperimentalFoundationApi
@Composable
fun AppUI(model: TheModel) {
    val scaffoldState = rememberScaffoldState()
    with(model){
        Crossfade(targetState = currentScreen) { screen ->
            when(screen){
                Screen.CATEGORY_SCREEN  -> { Category_Screen(model) } //todo: scaffold übergeben
                Screen.RECIPE_SCREEN    -> { Recipe_Screen(model)}
            }
        }
    }
}