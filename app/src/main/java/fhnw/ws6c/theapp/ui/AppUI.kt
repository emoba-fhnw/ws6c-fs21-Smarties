package fhnw.ws6c.theapp.ui

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import fhnw.ws6c.theapp.model.Screen
import fhnw.ws6c.theapp.model.TheModel
import fhnw.ws6c.theapp.ui.screens.Recipe_Screen
import fhnw.ws6c.theapp.ui.screens.Recipe_Step_Screen
import fhnw.ws6c.theapp.ui.screens.Tutorial_Screen

@ExperimentalFoundationApi
@Composable
fun AppUI(model: TheModel) {
    val scaffoldState = rememberScaffoldState()
    with(model){
        Crossfade(targetState = currentScreen) { screen ->
            when(screen){
                Screen.CATEGORY_SCREEN  -> { Category_Screen(model) } //todo: scaffold übergeben
                Screen.RECIPE_OVERVIEW_SCREEN    -> { Recipe_Screen(model)}
                Screen.TUTORIAL_SCREEN  -> { Tutorial_Screen(model)}
                Screen.RECIPE_STEPS_SCREEN  -> { Recipe_Step_Screen(model)}
            }
        }
    }
}