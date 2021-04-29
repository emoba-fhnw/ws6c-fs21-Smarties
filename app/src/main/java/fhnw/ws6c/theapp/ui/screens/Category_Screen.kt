package fhnw.ws6c.theapp.ui

import android.content.res.Resources
import android.graphics.Color
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.StarPurple500
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import fhnw.ws6c.theapp.model.Screen
import fhnw.ws6c.theapp.model.TheModel
import fhnw.ws6c.theapp.ui.theme.AppTheme

@ExperimentalFoundationApi
@Composable
fun Category_Screen(model: TheModel) {
    with(model) {
        AppTheme(darkTheme) {
            Scaffold(
                backgroundColor = MaterialTheme.colors.background,
                topBar = { TopBar(model, "Cocktails", Icons.Filled.Menu) },
                drawerContent = {}) { Body(model) }
        }
    }
}

@Composable
fun TopBar(model: TheModel, title: String, icon: ImageVector, onClickAct: () -> Unit = {}) {
    model.apply {
        TopAppBar(
            title = { Text(title, style = MaterialTheme.typography.h1, textAlign = TextAlign.Center
            ) },
            navigationIcon = {
                IconButton(onClick = { onClickAct() }) {
                    Icon(icon, "Menu")
                }
            }
        )
    }
}

@ExperimentalFoundationApi
@Composable
private fun Body(model: TheModel) {
    with(model) {
        loadDrinksOfChoosenCategoryAsync()

        LazyVerticalGrid(
            cells = GridCells.Adaptive(minSize = 100.dp),
            modifier = Modifier.padding(21.dp)
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
                        Box(Modifier.padding(0.dp, 60.dp, 0.dp, 0.dp)) {
                            Row() {
                                Text(it.name, style = MaterialTheme.typography.body1)
                                Icon(Icons.Rounded.StarPurple500, contentDescription = "Favorite")
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.padding(17.dp))
            }
        }
    }
}
