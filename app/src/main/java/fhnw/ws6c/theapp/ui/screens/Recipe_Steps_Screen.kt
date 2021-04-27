package fhnw.ws6c.theapp.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import fhnw.ws6c.theapp.model.Screen
import fhnw.ws6c.theapp.model.TheModel
import fhnw.ws6c.theapp.ui.TopBar
import fhnw.ws6c.theapp.ui.theme.AppTheme


@Composable
fun Recipe_Step_Screen(model: TheModel){
    with(model){
        AppTheme(darkTheme) {
            Scaffold(
                topBar = { TopBar(model, currentDrink.name, Icons.Filled.ArrowBack, {currentScreen = Screen.CATEGORY_SCREEN}) }){

            }
        }
    }
}


@Composable
fun Instruction_Box(){
    Box(
        modifier = Modifier
            .background(Color.LightGray)
            .verticalScroll(rememberScrollState())
            .padding(32.dp)
    ){

    }
}
