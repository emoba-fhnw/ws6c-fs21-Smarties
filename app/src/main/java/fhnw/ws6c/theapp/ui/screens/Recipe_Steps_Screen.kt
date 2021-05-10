package fhnw.ws6c.theapp.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
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
                    .padding(21.dp, 74.dp, 21.dp, 0.dp)
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
        Ingredients_BoxOfStep(model, recipeStep)

        Spacer(modifier = Modifier.padding(20.dp))

        Instruction_Box(model, recipeStep)

    }
}

@Composable
private fun Instruction_Box(model: CocktailModel, step: RecipeStep) {
    with(model) {
        Box(
            modifier = Modifier
                .border(1.dp, getColor(MyColors.Borders), RoundedCornerShape(10.dp))
                .requiredWidth(318.dp)
                .requiredHeight(288.dp)
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


@ExperimentalFoundationApi
@Composable
private fun Ingredients_BoxOfStep(model: CocktailModel, step: RecipeStep) {
    with(model) {
        LazyVerticalGrid(
            cells = GridCells.Adaptive(minSize = 100.dp),
            modifier = Modifier
                .padding(5.dp, 0.dp, 5.dp, 0.dp)
                .requiredHeight(112.dp),
        ) {
            items(step.ingredient.size) {
                Box(
                    modifier = Modifier.padding(0.dp),
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(modifier = Modifier.requiredSize(70.dp)) {
                            Image(
                                painterResource(id = getSvg(MySvgs.Circle)),
                                "Background",
                                modifier = Modifier.requiredSize(60.dp)
                            )
                            Image(
                                step.ingredient[it].img_small,
                                "Image of " + step.ingredient[it].name,
                                modifier = Modifier.size(60.dp),
                            )
                        }
                        Text(
                            step.meassurement[it] + " " + step.ingredient[it].name,
                            Modifier.requiredWidth(70.dp)
                        )
                        Spacer(modifier = Modifier.height(21.dp))
                    }
                }
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
                    .padding(5.dp, 0.dp, 5.dp, 0.dp),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { recording() }) {
                    if (isRecording.value) {
                        Image(
                            painterResource(id = getSvg(MySvgs.MicOn)),
                            contentDescription = "Microphone Off"
                        )
                    } else {
                        Image(
                            painterResource(id = getSvg(MySvgs.MicOff)),
                            contentDescription = "Microphone Off"
                        )

                    }
                }
            }
        }
    }
}


