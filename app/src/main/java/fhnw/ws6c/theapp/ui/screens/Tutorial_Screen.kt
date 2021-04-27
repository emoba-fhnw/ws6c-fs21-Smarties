package fhnw.ws6c.theapp.ui.screens

import android.text.Layout
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Swipe
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import fhnw.ws6c.theapp.model.Screen
import fhnw.ws6c.theapp.model.TheModel
import fhnw.ws6c.theapp.ui.TopBar
import fhnw.ws6c.theapp.ui.theme.AppTheme


@Composable
fun Tutorial_Screen(model: TheModel){
    with(model){
        AppTheme(darkTheme) {
            Scaffold(
                topBar = { TopBar(model, "Tutorial", Icons.Filled.ArrowBack, {currentScreen = Screen.CATEGORY_SCREEN}) }){
                Body(model)
            }
        }
    }
}


@Composable
private fun Body(model : TheModel){
    with(model){
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Use Speech Recognition")
            Icon(Icons.Filled.Mic, "Microphone")
            Text("Say Next")
            Text("If you want to move a step forward")
            Text("Say Back")
            Text("If you want to move a step backward")
            Text("Use Swipe")
            Icon(Icons.Filled.Swipe, "Swipe")
            Text("Don't want to use the option?")
            Text("Simply swipe to the left")
        }
    }
}