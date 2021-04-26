package fhnw.ws6c.theapp.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import fhnw.ws6c.theapp.model.Screen
import fhnw.ws6c.theapp.model.TheModel
import fhnw.ws6c.theapp.ui.theme.AppTheme


@ExperimentalFoundationApi
@Composable
fun Category_Screen(model: TheModel){
    with(model){
        AppTheme(darkTheme) {
            Scaffold(
                topBar = { TopBar(model, "Cocktails", Icons.Filled.Menu) },
                drawerContent = {}) { Body(model) }
        }
    }
}

@Composable
fun TopBar(model: TheModel, title : String, icon : ImageVector, onClickAct : () -> Unit = {}){
    with(model){
        TopAppBar(
            title = { Text(title, style = MaterialTheme.typography.h5,) },
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

        LazyVerticalGrid(cells = GridCells.Adaptive(minSize = 100.dp)) {
            items(category_model.drinksOfChoosenCategory) {
                Card(
                    modifier = Modifier.padding(9.dp).clickable(
                        onClick = {currentScreen = Screen.RECIPE_SCREEN; currentDrink = it; loadAllDrinkDetailsAsync()} )
                ) {
                    loadDrinkImgAsync(it)
                    Column() {
                        Image(it.preview_img, "Image of " + it.name)
                        Column(
                            modifier = Modifier.padding(4.dp)
                        ) {
                            Text("Name: " + it.name)
                        }
                    }
                }
                Spacer(modifier = Modifier.padding(17.dp))
            }
        }
    }
}

