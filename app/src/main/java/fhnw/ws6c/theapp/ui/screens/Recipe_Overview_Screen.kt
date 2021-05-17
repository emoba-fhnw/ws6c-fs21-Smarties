package fhnw.ws6c.theapp.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import fhnw.ws6c.theapp.model.Screen
import fhnw.ws6c.theapp.model.CocktailModel
import fhnw.ws6c.theapp.ui.TopBar
import fhnw.ws6c.theapp.ui.theme.AppTheme
import fhnw.ws6c.theapp.ui.theme.MyColors
import fhnw.ws6c.theapp.ui.theme.MySvgs

@ExperimentalFoundationApi
@Composable
fun Recipe_Screen(model: CocktailModel) {
    with(model) {
        AppTheme(darkTheme) {
            Scaffold(
                topBar = {
                    TopBar(
                        model,
                        currentDrink.name,
                        Icons.Filled.ArrowBackIos,
                        { currentScreen = Screen.CATEGORY_SCREEN })
                }) {
                Content(model)
            }
        }
    }
}

@ExperimentalFoundationApi
@Composable
private fun Content(model: CocktailModel) {
    with(model) {
        Row(
            modifier = Modifier.background(getColor(MyColors.Background))
        ){
            Column(
                modifier = Modifier.fillMaxSize().padding(21.dp, 0.dp, 21.dp, 0.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                loadDrinkImgAsync(currentDrink)
                Image(
                    currentDrink.img, "Image of " + currentDrink.name,
                    modifier = Modifier
                        .clip(CircleShape)
                        .align(Alignment.CenterHorizontally)
                        .size(150.dp)
                )

                Text("Ingredients ",
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier.align(Alignment.Start))

                Ingredients_Box(model)

                OutlinedButton(
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
                    onClick = { currentScreen = Screen.TUTORIAL_SCREEN; fillRecipeSteps() },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    border = BorderStroke(2.dp, getColor(MyColors.Borders))
                ) {
                    Text("Let's start", color = Color.White)
                }
            }

        }
    }
}

@ExperimentalFoundationApi
@Composable
fun Ingredients_Box(model: CocktailModel) {
    with(model) {
        LazyVerticalGrid(
            cells = GridCells.Adaptive(minSize = 100.dp),
            modifier = Modifier.padding(5.dp, 0.dp, 5.dp, 0.dp).requiredHeight(268.dp).background(Color.Transparent)
        ) {
            items(currentDrink.ingredients.size) {
                Box(
                    modifier = Modifier.padding(0.dp, 10.dp).border(0.dp, Color.Transparent),
                ) {
                    loadIngredientImgAsync(currentDrink.ingredients.get(it))
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(modifier = Modifier.requiredSize(70.dp)) {
                            Image(
                                painterResource(id = getSvg(MySvgs.Circle)), "Background",
                                modifier = Modifier.requiredSize(60.dp)
                            )
                            Image(
                                currentDrink.ingredients.get(it).img_small,
                                "Image of " + currentDrink.ingredients.get(it).name,
                                modifier = Modifier.size(60.dp),
                            )
                        }
                        Text(currentDrink.meassurements.get(it) + " " + currentDrink.ingredients.get(it).name,
                            modifier = Modifier.requiredWidth(70.dp), textAlign = TextAlign.Center)
                    }
                }
            }
        }
    }
}

