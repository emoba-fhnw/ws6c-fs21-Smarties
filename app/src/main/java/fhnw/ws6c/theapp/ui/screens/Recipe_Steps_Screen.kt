package fhnw.ws6c.theapp.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import fhnw.ws6c.R
import fhnw.ws6c.theapp.TheApp.model
import fhnw.ws6c.theapp.model.Screen
import fhnw.ws6c.theapp.model.CocktailModel
import fhnw.ws6c.theapp.ui.TopBar
import fhnw.ws6c.theapp.ui.theme.AppTheme

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
                        { currentScreen = Screen.RECIPE_OVERVIEW_SCREEN })
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(21.dp, 74.dp, 21.dp, 0.dp)
                .clickable {
                    currentScreen = Screen.DRINK_COMPLETED_SCREEN
                }, horizontalAlignment = Alignment.CenterHorizontally
        ) {
            loadDrinkImgAsync(currentDrink)
            Ingredients_Box(model)
            Spacer(modifier = Modifier.height(65.dp))
            Instruction_Box()
        }

    }
}

@Composable
fun Instruction_Box() {
    with(model) {
        Box(
            modifier = Modifier.border(
                1.dp,
                Brush.horizontalGradient(colors = listOf(Color(0xFFFF00F5), Color(0xFF95A5F5))),
                RoundedCornerShape(10.dp)

            ).requiredWidth(318.dp).requiredHeight(288.dp)
        ) {
            /*   Image(
                   painterResource(id = R.drawable.ic_instructionsbox),
                   "Instructions",
                   modifier = Modifier
                       .requiredHeight(288.dp)
                       .requiredWidth(318.dp)
               )
               Card(
                   modifier = Modifier
                       .background(Color.Black.copy(alpha = 1f))
                       .verticalScroll(rememberScrollState())
                       //  .padding(32.dp)
                       .border(2.dp, Color.White)
               ){*/
            //loadAllDrinkDetailsAsync()
            Column(modifier = Modifier.padding(18.dp)) {
                Text(
                    "Instructions ",
                    style = MaterialTheme.typography.caption,
                    fontWeight = FontWeight.Thin,
                   // modifier = Modifier.
                    //brush = Brush.horizontalGradient(colors = listOf(Color(0xFFFF00F5), Color(0xFF95A5F5))),
                    color = Color.White
                )
                Text(
                    currentDrink.instructions,
                    style = MaterialTheme.typography.caption,
                    color = Color.White
                )
            }

            //   }
        }

    }
}
