package fhnw.ws6c.theapp.ui

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import fhnw.ws6c.R
import fhnw.ws6c.theapp.model.Screen
import fhnw.ws6c.theapp.model.CocktailModel
import fhnw.ws6c.theapp.ui.theme.AppTheme

@ExperimentalFoundationApi
@Composable
fun Category_Screen(model: CocktailModel){
    with(model){
        AppTheme(darkTheme) {
            Scaffold(
                topBar = { TopBar(model, "Cocktails", Icons.Rounded.Menu) },
                drawerContent = {}) { Body(model) }
        }
    }
}

@Composable
fun TopBar(model: CocktailModel, title : String, icon : ImageVector, onClickAct : () -> Unit = {}){
    with(model){
        TopAppBar(
            modifier = Modifier.wrapContentWidth(Alignment.CenterHorizontally),
            title = {
                Text(
                    title,
                    style = MaterialTheme.typography.h1,
//                    textAlign = TextAlign.Center,
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
        loadDrinksOfChoosenCategoryAsync()

        LazyVerticalGrid(
            cells = GridCells.Adaptive(minSize = 100.dp),
            modifier = Modifier.padding(21.dp, 0.dp, 21.dp, 0.dp)
        ) {
            items(currentCategory.listOfDrinks) {
                Card(
                    modifier = Modifier
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
                            modifier = Modifier.requiredSize(100.dp)
                        )
                        Box(
                            Modifier
                                .padding(0.dp, 60.dp, 0.dp, 0.dp)
                                .background(Color(0xCCFFFFFF))
                        ) {      // Opacity 80%
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
//                                    Icon(
//                                        Icons.Rounded.Star,
//                                        contentDescription = "Favorite",
//                                        modifier = Modifier.requiredSize(27.dp),
//                                    )
                                    IconButton(onClick = { /*TODO*/ }) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.icon_star_unfilled),
                                            contentDescription = "No favourite",
                                            modifier = Modifier.requiredSize(27.dp)
                                        )
                                    }
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