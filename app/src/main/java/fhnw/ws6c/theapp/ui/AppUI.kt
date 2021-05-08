package fhnw.ws6c.theapp.ui

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import fhnw.ws6c.theapp.model.Screen
import fhnw.ws6c.theapp.model.CocktailModel
import fhnw.ws6c.theapp.ui.screens.Drink_Completed_Screen
import fhnw.ws6c.theapp.ui.screens.Recipe_Screen
import fhnw.ws6c.theapp.ui.screens.Recipe_Steps_Screen
import fhnw.ws6c.theapp.ui.screens.Tutorial_Screen

@ExperimentalFoundationApi
@Composable
fun AppUI(model: CocktailModel) {
    val scaffoldState = rememberScaffoldState()
    with(model){
        Crossfade(targetState = currentScreen) { screen ->
            when(screen){
                Screen.CATEGORY_SCREEN  -> { Category_Screen(model) } //todo: scaffold übergeben
                Screen.RECIPE_OVERVIEW_SCREEN    -> { Recipe_Screen(model)}
                Screen.TUTORIAL_SCREEN  -> { Tutorial_Screen(model)}
                Screen.RECIPE_STEPS_SCREEN  -> { Recipe_Steps_Screen(model)}
                Screen.DRINK_COMPLETED_SCREEN -> { Drink_Completed_Screen(model)}
            }
        }
    }
}