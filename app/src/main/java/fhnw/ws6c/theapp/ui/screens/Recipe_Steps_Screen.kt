package fhnw.ws6c.theapp.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import fhnw.ws6c.R
import fhnw.ws6c.theapp.TheApp.model
import fhnw.ws6c.theapp.data.RecipeStep
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
//            .swipeable(state = rememberSwipeableState(initialValue = 0), )
            .clickable{
                if(currentRecipeStepIndex < recipeSteps.size-1){
                    currentRecipeStepIndex ++
                }
                else{
                    currentScreen = Screen.DRINK_COMPLETED_SCREEN
                    currentRecipeStepIndex = 0
                }
            }
            , horizontalAlignment = Alignment.CenterHorizontally) {
            loadDrinkImgAsync(currentDrink)

            Step_Content(model, recipeSteps[currentRecipeStepIndex])
        }

    }
}

@ExperimentalFoundationApi
@Composable
private fun Step_Content(model: CocktailModel, recipeStep: RecipeStep){
    with(model){
        Ingredients_BoxOfStep(model, recipeStep)

        Spacer(modifier = Modifier.padding(20.dp))

        Text("Instructions: ")

        Instruction_Box(model, recipeStep)

    }
}

@Composable
private fun Instruction_Box(model : CocktailModel, step : RecipeStep){
    with(model){
        Box(
            modifier = Modifier
                .background(Color.Black)
                .verticalScroll(rememberScrollState())
                .padding(32.dp)
        ){
            Text(step.instruction)
        }
    }
}


@ExperimentalFoundationApi
@Composable
private fun Ingredients_BoxOfStep(model: CocktailModel, step: RecipeStep) {
    with(model) {
        LazyVerticalGrid(
            cells = GridCells.Adaptive(minSize = 100.dp),
            modifier = Modifier.padding(5.dp, 0.dp, 5.dp, 0.dp),
        ) {
            items(step.ingredient.size) {
                Card(
                    modifier = Modifier
                        .padding(0.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(modifier = Modifier.requiredSize(70.dp)) {
                            Image(
                                painterResource(id = R.drawable.ic_circle),
                                "Background",
                                modifier = Modifier.requiredSize(60.dp)
                            )
                            Image(
                                step.ingredient[it].img_small,
                                "Image of " + step.ingredient[it].name,
                                modifier = Modifier
                                    .size(60.dp),
                            )
                        }
                        Text(step.meassurement[it] + " " +  step.ingredient[it].name,
                            Modifier
                                .requiredWidth(70.dp)
                        )
                        Spacer(modifier = Modifier.height(21.dp))
                    }
                }
            }
        }
    }
}
