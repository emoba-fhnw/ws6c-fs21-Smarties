package fhnw.ws6c.theapp.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import fhnw.ws6c.theapp.TheApp.model
import fhnw.ws6c.theapp.model.Screen
import fhnw.ws6c.theapp.model.CocktailModel
import fhnw.ws6c.theapp.ui.TopBar
import fhnw.ws6c.theapp.ui.theme.AppTheme

@ExperimentalFoundationApi
@Composable
fun Recipe_Steps_Screen(model: CocktailModel){
    with(model){
        AppTheme(darkTheme) {
            Scaffold(
                topBar = { TopBar(model, currentDrink.name, Icons.Filled.Close, {currentScreen = Screen.RECIPE_OVERVIEW_SCREEN}) }){
                Content(model)
            }
        }
    }
}

@ExperimentalFoundationApi
@Composable
private fun Content(model: CocktailModel){
    with(model){
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(22.dp)
            .clickable {
                currentScreen = Screen.DRINK_COMPLETED_SCREEN
            }
            , horizontalAlignment = Alignment.CenterHorizontally) {
            loadDrinkImgAsync(currentDrink)
            Ingredients_Box(model)

            Spacer(modifier = Modifier.padding(20.dp))

            Text("Instructions: ")
            Instruction_Box()
        }

    }
}

@Composable
fun Instruction_Box(){
    with(model){
        Box(
            modifier = Modifier
                .background(Color.LightGray)
                .verticalScroll(rememberScrollState())
                .padding(32.dp)
        ){
//            loadAllDrinkDetailsAsync()
            Text(currentDrink.instructions)
        }
    }
}
