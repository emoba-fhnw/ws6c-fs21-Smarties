package fhnw.ws6c.theapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import fhnw.ws6c.R
import fhnw.ws6c.theapp.model.Screen
import fhnw.ws6c.theapp.model.CocktailModel
import fhnw.ws6c.theapp.ui.TopBar
import fhnw.ws6c.theapp.ui.theme.AppTheme


@Composable
fun Tutorial_Screen(model: CocktailModel){
    with(model){
        AppTheme(darkTheme) {
            Scaffold(
                topBar = { TopBar(model, "Tutorial", Icons.Filled.ArrowBackIos, {currentScreen = Screen.RECIPE_OVERVIEW_SCREEN}) }){
                Body(model)
            }
        }
    }
}


@Composable
private fun Body(model : CocktailModel){
    with(model){
        Column(modifier = Modifier.fillMaxSize().clickable { currentScreen = Screen.RECIPE_STEPS_SCREEN },
            horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.padding(20.dp))
            Text("Use Speech Recognition", fontStyle = FontStyle.Italic)
            Spacer(modifier = Modifier.padding(20.dp))
            Image(painterResource(id = R.drawable.ic_mic), "Microphone", modifier = Modifier.height(84.dp))
            Text("Say Next")
            Text("If you want to move a step forward")
            Spacer(modifier = Modifier.padding(20.dp))
            Text("Say Back")
            Text("If you want to move a step backward")
            Spacer(modifier = Modifier.padding(20.dp))
            Text("Use Swipe", fontStyle = FontStyle.Italic)
            Image(painterResource(id = R.drawable.ic_swipe), "Swipe", modifier = Modifier.height(142.dp))
            Spacer(modifier = Modifier.padding(20.dp))
            Text("Don't want to use the option?")
            Text("Simply swipe to the left")
        }
    }
}