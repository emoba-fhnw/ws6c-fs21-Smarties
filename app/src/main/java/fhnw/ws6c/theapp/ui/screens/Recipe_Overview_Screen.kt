package fhnw.ws6c.theapp.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import fhnw.ws6c.theapp.model.Screen
import fhnw.ws6c.theapp.model.CocktailModel
import fhnw.ws6c.theapp.ui.TopBar
import fhnw.ws6c.theapp.ui.theme.AppTheme

@ExperimentalFoundationApi
@Composable
fun Recipe_Screen(model: CocktailModel){
    with(model){
        AppTheme(darkTheme) {
            Scaffold(
                topBar = { TopBar(model, currentDrink.name, Icons.Filled.ArrowBackIos, {currentScreen = Screen.CATEGORY_SCREEN}) }){
                Content(model)
            }
        }
    }
}

@ExperimentalFoundationApi
@Composable
private fun Content(model: CocktailModel){
    with(model){
        Column(modifier = Modifier.fillMaxSize().padding(22.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {
            loadDrinkImgAsync(currentDrink)
            Image(currentDrink.img, "Image of " + currentDrink.name,
                modifier = Modifier
                    .clip(CircleShape)
                    .align(Alignment.CenterHorizontally)
                    .size(150.dp))
            Text(currentDrink.name, modifier = Modifier.align(Alignment.CenterHorizontally))

            Spacer(modifier = Modifier.padding(20.dp))

            Text("Ingredients: ")
            Ingredients_Box(model)
            Spacer(modifier = Modifier.padding(20.dp))
            OutlinedButton(onClick = {currentScreen = Screen.TUTORIAL_SCREEN},
            modifier = Modifier.align(Alignment.CenterHorizontally).fillMaxWidth()){
                Text("Let's start")
            }
        }

    }
}

@ExperimentalFoundationApi
@Composable
fun Ingredients_Box(model: CocktailModel){
    with(model){
        Box(
            modifier = Modifier
                .background(Color.LightGray)
                .horizontalScroll(rememberScrollState())
                .fillMaxWidth()
                .border(2.dp, Color.Black)
        ){
            LazyColumn(
                modifier = Modifier.height(200.dp)
            ) {
                items(currentDrink.ingredients.size){
                    Card(
                        modifier = Modifier
                            .padding(9.dp)
                            .width(100.dp)
                            .height(112.dp)
                    ) {
                        loadIngredientImgAsync(currentDrink.ingredients.get(it))
                        Image(currentDrink.ingredients.get(it).img_small, "Image of " + currentDrink.ingredients.get(it).name)
                        Text(currentDrink.meassurements.get(it) + " " + currentDrink.ingredients.get(it).name)
                    }
                }
            }
        }
    }
}