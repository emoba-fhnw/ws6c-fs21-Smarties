package fhnw.ws6c.theapp.ui

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import fhnw.ws6c.theapp.model.Screen
import fhnw.ws6c.theapp.model.CocktailModel
import fhnw.ws6c.theapp.ui.theme.AppTheme
import fhnw.ws6c.theapp.ui.theme.MyColors
import fhnw.ws6c.theapp.ui.theme.MySvgs

@ExperimentalFoundationApi
@Composable
fun Category_Screen(model: CocktailModel){
    with(model){
        AppTheme(darkTheme) {
            Scaffold(
                topBar = { TopBar(model, "Cocktails", Icons.Rounded.Menu, {darkTheme = !darkTheme}) },
                drawerContent = {}) { Body(model) }
        }
    }
}

@Composable
fun TopBar(model: CocktailModel, title : String, icon : ImageVector, onClickAct : () -> Unit = {}){
    with(model){
        TopAppBar(
            backgroundColor = MaterialTheme.colors.background,
            modifier = Modifier.wrapContentWidth(Alignment.CenterHorizontally)
                .border(
                    1.dp,
                    Color.Transparent),
            title = {
                Text(
                    title,
                    style = MaterialTheme.typography.h1,
                    color = MaterialTheme.colors.primary,
                )
            },
            navigationIcon = {
                IconButton(
                    onClick = { onClickAct() },
                    modifier = Modifier.padding(21.dp, 0.dp, 21.dp, 0.dp)
                ) {
                    Icon(icon, "Menu")
                }
            }
        )
    }
}

@ExperimentalFoundationApi
@Composable
private fun Body(model: CocktailModel) {
    with(model) {
        if(currentCategory.listOfDrinks.isEmpty()){
            loadDrinksOfChoosenCategoryAsync()
        }

        Row(
            modifier = Modifier.background(getColor(MyColors.Background))
        ){
            LazyVerticalGrid(
                cells = GridCells.Adaptive(minSize = 100.dp),
                modifier = Modifier.padding(21.dp, 0.dp, 21.dp, 0.dp).background(Color.Transparent)
            ) {
                items(currentCategory.listOfDrinks) {
                    Card(
                        modifier = Modifier
                            .border(0.dp, Color.Transparent, RoundedCornerShape(10.dp))
                            .padding(4.5.dp)
                            .clickable(
                                onClick = {
                                    currentScreen = Screen.RECIPE_OVERVIEW_SCREEN; currentDrink =
                                    it; loadAllDrinkDetailsAsync()
                                })
                    ) {
                        loadDrinkImgAsync(it)
                        Box(Modifier.requiredSize(100.dp)) {
                            Image(
                                it.preview_img,
                                "Image of " + it.name,
                                modifier = Modifier.fillMaxSize()
                            )
                            Box(
                                Modifier
                                    .padding(0.dp, 60.dp, 0.dp, 0.dp)
                                    .background(Color(0xCCFFFFFF)) //Background of Text in card
                            ) {
                                Row(
                                    Modifier.padding(4.dp, 4.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                ) {
                                    Box(
                                        Modifier
                                            .padding(2.dp)
                                            .requiredWidth(64.dp)
                                    ) {
                                        Text(
                                            it.name,
                                            style = MaterialTheme.typography.body1,
                                            color = Black
                                        )
                                    }
                                    Box(Modifier.padding(2.dp)) {
                                        //Favourite
                                        IconButton(onClick = { checkAndSetFavourite(it) }) {
                                            if (it.isFavorite) {
                                                Image(
                                                    painterResource(id = getSvg(MySvgs.StarFilled)),
                                                    contentDescription = "Favourite",
                                                    modifier = Modifier.requiredSize(27.dp)
                                                )
                                            } else {
                                                Image(
                                                    painterResource(id = getSvg(MySvgs.StarUnfilled)),
                                                    contentDescription = "No Favourite",
                                                    modifier = Modifier.requiredSize(27.dp)
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                            Spacer(modifier = Modifier.padding(17.dp))
                        }
                    }
                }
            }
        }
    }
}