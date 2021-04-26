package fhnw.ws6c.theapp.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import fhnw.ws6c.theapp.model.TheModel


@ExperimentalFoundationApi
@Composable
fun AppUI(model: TheModel) {
    val scaffoldState = rememberScaffoldState()

    model.apply {
        Scaffold(scaffoldState = scaffoldState,
                topBar = { Bar(model, scaffoldState) },
//                bodyContent = { Body(model) }
        ) {
            Body(model)
        }
    }
}

@Composable
private fun Bar(model: TheModel, scaffoldState: ScaffoldState) {
    model.apply {
        TopAppBar(title = { Text("Cocktails") },
            navigationIcon = {
//                IconButton(onClick = { scaffoldState.drawerState.open() }) {
//                    Icon(Icons.Filled.Menu)
//                }
            }
        )
    }
}

@ExperimentalFoundationApi
@Composable
private fun Body(model: TheModel) {
    model.apply {
        Column() {
            Row() {
                Button(onClick = { loadDrinksOfChoosenCategoryAsync() }) {
                    Text("load drinks")
                }
            }
            // Cocktail
            LazyVerticalGrid(cells = GridCells.Adaptive(minSize = 100.dp)) {
                items(category_model.drinksOfChoosenCategory) {
                    Card(
//                            border = BorderStroke(2.dp, Color.Black),
//                            backgroundColor = Color.Cyan,
                        modifier = Modifier.padding(9.dp)
                    ) {
                        loadDrinkImgAsync(it)
                        Column() {
                            Image(it.preview_img, "Image of " + it.name)
                            Column(
                                modifier = Modifier
                                    .padding(4.dp)
//                                        .fillMaxWidth()
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
}

