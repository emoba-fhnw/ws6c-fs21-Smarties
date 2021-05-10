package fhnw.ws6c.theapp.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fhnw.ws6c.R
import fhnw.ws6c.theapp.model.Screen
import fhnw.ws6c.theapp.model.CocktailModel
import fhnw.ws6c.theapp.ui.TopBar
import fhnw.ws6c.theapp.ui.theme.AppTheme


@Composable
fun Drink_Completed_Screen(model: CocktailModel) {
    with(model) {
        AppTheme(darkTheme) {
            Scaffold() {
                Content(model)
            }
        }
    }
}

@Composable
private fun Content(model: CocktailModel) {
    with(model) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(21.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painterResource(id = R.drawable.ic_cocktail),
                "Cocktail",
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(21.dp))
            Text(
                "Enjoy your drink".toUpperCase(),
                modifier = Modifier.align(CenterHorizontally),
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                "You successfully finished mixing",
                modifier = Modifier.align(CenterHorizontally),
                fontSize = 16.sp
            )
            Text(
                currentDrink.name.toUpperCase(),
                modifier = Modifier.align(CenterHorizontally),
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(48.dp))
            Text(
                "Did you like the drink?",
                modifier = Modifier.align(CenterHorizontally),
                fontSize = 16.sp
            )
            IconButton(onClick = { /*TODO*/ }) {
                Image(
                    painter = painterResource(id = R.drawable.icon_star_unfilled),
                    contentDescription = "No favourite",
                    modifier = Modifier
                        .requiredSize(27.dp)
                        .align(CenterHorizontally)
                )
            }
            Spacer(modifier = Modifier.height(48.dp))
            Row(verticalAlignment = Alignment.Bottom) {
                Column() {
                    OutlinedButton(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .align(CenterHorizontally)
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(20.dp),
                        border = BorderStroke(
                            2.dp,
                            Brush.horizontalGradient(
                                colors = listOf(
                                    Color(0xFFFF00F5),
                                    Color(0xFF95A5F5)
                                )
                            )
                        )
                    ) {
                        Text("Go to my bar", color = MaterialTheme.colors.onSecondary)
                    }
                    Spacer(modifier = Modifier.height(21.dp))
                    OutlinedButton(
                        onClick = { currentScreen = Screen.CATEGORY_SCREEN },
                        modifier = Modifier
                            .align(CenterHorizontally)
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(20.dp),
                        border = BorderStroke(
                            2.dp,
                            Brush.horizontalGradient(
                                colors = listOf(
                                    Color(0xFFFF00F5),
                                    Color(0xFF95A5F5)
                                )
                            )
                        )
                    ) {
                        Text("Give me more cocktails", color = MaterialTheme.colors.onSecondary)
                    }
                }
            }
        }
    }
}