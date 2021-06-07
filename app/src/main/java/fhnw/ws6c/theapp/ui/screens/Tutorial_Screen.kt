package fhnw.ws6c.theapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fhnw.ws6c.theapp.model.Screen
import fhnw.ws6c.theapp.model.CocktailModel
import fhnw.ws6c.theapp.ui.TopBar
import fhnw.ws6c.theapp.ui.theme.AppTheme
import fhnw.ws6c.theapp.ui.theme.MyColors
import fhnw.ws6c.theapp.ui.theme.MySvgs
import java.time.format.TextStyle


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
        var offsetX by remember{ mutableStateOf(0f)}
        val state = rememberDraggableState {delta -> offsetX += delta}
        recording()
        Row(
            modifier = Modifier.background(getColor(MyColors.Background))
        ){
            Column( modifier = Modifier
                .fillMaxSize()
                .draggable(state = state,
                    orientation = Orientation.Horizontal,
                    onDragStopped = {
                        if (offsetX < -200) {
                            stopRecording() //stop recording, when user decides to use swiping
                            currentScreen = Screen.RECIPE_STEPS_SCREEN;
                            offsetX = 0f}
                    }),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly) {

                //Speech
                Text("Use Speech Recognition", fontStyle = FontStyle.Italic)
                Image(painterResource(id = getSvg(MySvgs.Microphone)), "Microphone", modifier = Modifier.height(84.dp))
                Column(modifier = Modifier.padding(0.dp, 12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally) {

                    Column(horizontalAlignment = Alignment.CenterHorizontally){
                        Row(verticalAlignment = Alignment.Bottom){
                            Text("Say ")
                            Text("NEXT", fontWeight = FontWeight.Black)
                        }
                        Text("If you want to move a step forward")
                    }

                    Spacer(modifier = Modifier.padding(4.dp))

                    Column(horizontalAlignment = Alignment.CenterHorizontally){
                        Row(verticalAlignment = Alignment.Bottom){
                            Text("Say ")
                            Text("BACK", fontWeight = FontWeight.Black)
                        }
                        Text("If you want to move a step backward")
                    }

                    Spacer(modifier = Modifier.padding(4.dp))

                    Column(horizontalAlignment = Alignment.CenterHorizontally){
                        Row(verticalAlignment = Alignment.Bottom){
                            Text("Say ")
                            Text("STOP", fontWeight = FontWeight.Black)
                        }
                        Text("If you want to stop the Speech Recognition")
                    }
                }

                Spacer(modifier = Modifier.padding(4.dp))

                //Swipe
                Text("Use Swipe", fontStyle = FontStyle.Italic)
                Image(painterResource(id = getSvg(MySvgs.Swipe)), "Swipe", modifier = Modifier.height(84.dp))
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Don't want to use the option?")
                    Text("Simply swipe to the left")
                }

            }
        }
    }
}