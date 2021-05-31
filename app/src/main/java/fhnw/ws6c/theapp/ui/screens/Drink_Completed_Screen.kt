package fhnw.ws6c.theapp.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fhnw.ws6c.R
import fhnw.ws6c.theapp.model.Screen
import fhnw.ws6c.theapp.model.CocktailModel
import fhnw.ws6c.theapp.ui.theme.AppTheme
import fhnw.ws6c.theapp.ui.theme.MyColors
import fhnw.ws6c.theapp.ui.theme.MySvgs


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
        Row(
            modifier = Modifier.background(getColor(MyColors.Background))
        ){
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(21.dp, 0.dp, 21.dp, 0.dp),
                horizontalAlignment = CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly,
            ) {
                Image(
                    painterResource(id = R.drawable.ic_cocktail),
                    "Cocktail",
                    modifier = Modifier.fillMaxWidth()
                )

                Text(
                    "Enjoy your drink".toUpperCase(),
                    modifier = Modifier.align(CenterHorizontally),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                )

                Column() {
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
                }
<<<<<<< HEAD
            }
            OutlinedButton(
                onClick = { currentScreen = Screen.FAVOURITE_SCREEN },
                modifier = Modifier
                    .align(CenterHorizontally)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                border = BorderStroke(2.dp, getColor(MyColors.Borders))
            ) {
                Text("Go to my bar", color = Color.White)
            }
=======
>>>>>>> 9e766f6f7431a9888c89814db9b8514681b980d9

                Column(horizontalAlignment = CenterHorizontally,){
                    Text(
                        "Did you like the drink?",
                        modifier = Modifier.align(CenterHorizontally),
                        fontSize = 16.sp
                    )
                    IconButton(onClick = { /*TODO*/ }) {
                        Image(
                            painter = painterResource(id = getSvg(MySvgs.StarUnfilled)),
                            contentDescription = "No favourite",
                            modifier = Modifier
                                .requiredSize(27.dp)
                                .align(CenterHorizontally)
                        )
                    }
                }
                OutlinedButton(
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .align(CenterHorizontally)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    border = BorderStroke(2.dp, getColor(MyColors.Borders))
                ) {
                    Text("Go to my bar", color = Color.White)
                }

                OutlinedButton(
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
                    onClick = { currentScreen = Screen.CATEGORY_SCREEN },
                    modifier = Modifier
                        .align(CenterHorizontally)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    border = BorderStroke(2.dp, getColor(MyColors.Borders))
                ) {
                    Text("Give me more cocktails", color = Color.White)
                }

                OutlinedButton(
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
                    onClick = { currentScreen = Screen.RECIPE_OVERVIEW_SCREEN },
                    modifier = Modifier
                        .align(CenterHorizontally)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    border = BorderStroke(2.dp, getColor(MyColors.Borders))
                ) {
                    Text("Mix it again", color = Color.White)
                }
            }
        }
    }
}