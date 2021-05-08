package fhnw.ws6c.theapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fhnw.ws6c.R
import fhnw.ws6c.theapp.model.Screen
import fhnw.ws6c.theapp.model.CocktailModel
import fhnw.ws6c.theapp.ui.theme.AppTheme


@Composable
fun Drink_Completed_Screen(model: CocktailModel){
    with(model){
        AppTheme(darkTheme) {
            Content(model)
        }
    }
}

@Composable
private fun Content(model: CocktailModel){
    with(model){
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(22.dp)) {
            Image(painterResource(id = R.drawable.ic_cocktail), "Cocktail", modifier = Modifier.fillMaxWidth())
            Text("Enjoy your drink".toUpperCase(), modifier = Modifier.align(CenterHorizontally), fontSize = 24.sp)
            Spacer(modifier = Modifier.height(24.dp))
            Text("You successfully finished mixing", modifier = Modifier.align(CenterHorizontally), fontSize = 16.sp)
            Text(currentDrink.name.toUpperCase(), modifier = Modifier.align(CenterHorizontally), fontSize = 16.sp)
            Spacer(modifier = Modifier.height(48.dp))
            Row(verticalAlignment = Alignment.Bottom){
                Column() {
                    OutlinedButton(onClick = { /*TODO*/ },
                        modifier = Modifier.align(CenterHorizontally).fillMaxWidth()) {
                        Text("Go To my bar")
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                    OutlinedButton(onClick = { currentScreen = Screen.CATEGORY_SCREEN },
                        modifier = Modifier.align(CenterHorizontally).fillMaxWidth()) {
                        Text("Give me more cocktails")
                    }
                }
            }
        }
    }
}