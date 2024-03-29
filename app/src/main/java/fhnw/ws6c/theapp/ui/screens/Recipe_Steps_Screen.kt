package fhnw.ws6c.theapp.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import fhnw.ws6c.theapp.data.RecipeStep
import fhnw.ws6c.theapp.model.Screen
import fhnw.ws6c.theapp.model.CocktailModel
import fhnw.ws6c.theapp.ui.TopBar
import fhnw.ws6c.theapp.ui.theme.AppTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.style.TextAlign
import fhnw.ws6c.R
import fhnw.ws6c.theapp.ui.theme.MyColors
import fhnw.ws6c.theapp.ui.theme.MySvgs

@ExperimentalFoundationApi
@Composable
fun Recipe_Steps_Screen(model: CocktailModel) {
    with(model) {
        AppTheme(darkTheme) {
            Scaffold(
                topBar = {
                    TopBar(
                        model,
                        currentDrink.name,
                        Icons.Filled.Close,
                        { currentScreen = Screen.RECIPE_OVERVIEW_SCREEN; currentRecipeStepIndex = 0 })
                },
                bottomBar = { BottomAppBar(model) }) {
                Content(model)
            }
        }
    }
}

@ExperimentalFoundationApi
@Composable
private fun Content(model: CocktailModel) {
    with(model) {
        var offsetX by remember { mutableStateOf(0f) }
        val state = rememberDraggableState { delta -> offsetX += delta }
        Row(
            modifier = Modifier.background(getColor(MyColors.Background))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .draggable(
                        state = state,
                        orientation = Orientation.Horizontal,
                        onDragStopped = {
                            if (offsetX > 200) {
                                if (currentRecipeStepIndex > 0) {
                                    currentRecipeStepIndex--
                                }
                            } else if (offsetX < -200) {
                                if (currentRecipeStepIndex < recipeSteps.size - 1) {
                                    currentRecipeStepIndex++
                                } else {
                                    currentScreen = Screen.DRINK_COMPLETED_SCREEN
                                    currentRecipeStepIndex = 0
                                }
                            }
                            offsetX = 0f
                            println("DragStopped"); println(state.toString()); println("OffsetX: " + offsetX)
                        }), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                loadDrinkImgAsync(currentDrink)

                Step_Content(model, recipeSteps[currentRecipeStepIndex])
            }
        }
    }
}

@ExperimentalFoundationApi
@Composable
private fun Step_Content(model: CocktailModel, recipeStep: RecipeStep) {
    with(model) {
        Column(
            modifier = Modifier.fillMaxSize().padding(21.dp, 0.dp, 21.dp, 60.dp),
            verticalArrangement = Arrangement.SpaceEvenly) {

            Text("Required Ingredients ",
                style = MaterialTheme.typography.body2,
                modifier = Modifier.align(Alignment.Start))

            Ingredients_BoxOfStep(model, recipeStep)

            Instruction_Box(model, recipeStep)
        }
    }
}


@ExperimentalFoundationApi
@Composable
private fun Ingredients_BoxOfStep(model: CocktailModel, step: RecipeStep) {
    with(model) {
        LazyVerticalGrid(
            cells = GridCells.Adaptive(minSize = 100.dp),
            modifier = Modifier
                .padding(5.dp, 0.dp, 5.dp, 0.dp)
                .requiredHeight(200.dp),
        ) {
            items(step.ingredient.size) {
                Box(
                    modifier = Modifier.padding(0.dp, 6.dp),
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(modifier = Modifier.requiredSize(42.dp)) {
                            Image(
                                painterResource(id = getSvg(MySvgs.Circle)),
                                "Background",
                                modifier = Modifier.requiredSize(40.dp)
                            )
                            Image(
                                step.ingredient[it].img_small,
                                "Image of " + step.ingredient[it].name,
                                modifier = Modifier.size(40.dp),
                            )
                        }
                        Text(
                            step.meassurement[it] + " " + step.ingredient[it].name,
                            modifier = Modifier.requiredWidth(70.dp), textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun Instruction_Box(model: CocktailModel, step: RecipeStep) {
    with(model) {
        Box(
            modifier = Modifier
                .border(1.dp, getColor(MyColors.Borders), RoundedCornerShape(10.dp))
                .fillMaxWidth()
                .requiredHeight(250.dp)
                .background(Color.Transparent)
                .verticalScroll(rememberScrollState())
        ) {
            Column(modifier = Modifier.padding(15.dp)) {
                Text(
                    "Instructions",
                    style = MaterialTheme.typography.caption,
                    fontWeight = FontWeight.Thin,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(21.dp))
                Text(
                    step.instruction, style = MaterialTheme.typography.caption,
                    color = Color.White
                )
            }
        }
    }
}





@Composable
fun BottomAppBar(model : CocktailModel) {
    with(model){
        BottomAppBar(backgroundColor = Color.Transparent){
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(53.dp, 0.dp, 5.dp, 0.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("")
                stepCircles(model)
                microphone(model)
            }
        }
    }
}


@Composable
fun stepCircles(model : CocktailModel){
    with(model){
        LazyRow(
        ){
            items(recipeSteps.size){
                if(currentRecipeStepIndex === it){
                    Image(
                        painterResource(id = getSvg(MySvgs.FilledStepCircle)),
                        contentDescription = "Current step",
                        modifier = Modifier.padding(2.dp, 0.dp, 2.dp, 0.dp)
                    )
                }else{
                    Image(
                        painterResource(id = R.drawable.ic_step_circle_transparent),
                        contentDescription = "not current step",
                        modifier = Modifier.padding(2.dp, 0.dp, 2.dp, 0.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun microphone(model : CocktailModel){
    with(model){
        IconButton(onClick = { if(isRecording.value){ stopRecording() }else{ enableSpeechRec = true; recording() }}) {
            if (!isRecording.value) {
                Image(
                    painterResource(id = getSvg(MySvgs.MicOff)),
                    contentDescription = "Microphone off"
                )
            } else {
                Image(
                    painterResource(id = getSvg(MySvgs.MicOn)),
                    contentDescription = "Microphone on"
                )
            }
        }
    }
}