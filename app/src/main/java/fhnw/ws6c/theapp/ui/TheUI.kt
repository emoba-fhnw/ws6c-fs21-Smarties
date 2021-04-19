package fhnw.ws6c.theapp.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import fhnw.ws6c.theapp.model.TheModel


@Composable
fun AppUI(model : TheModel){
    with(model){
        Column() {
            Row(){
                Button(onClick = {loadDrinksOfChoosenCategoryAsync()}) {
                    Text("load drinks")
                }
            }
            Row(){
                LazyColumnFor(items = category_model.drinksOfChoosenCategory){
                    Card(
                        border = BorderStroke(2.dp, Color.Black),
                        backgroundColor = Color.Cyan,
                        modifier = Modifier.padding(2.dp)
                    ) {
                        Column(modifier = Modifier.padding(4.dp)) {
                            Text("Name: " + it.name)
                            Text("Id: " + it.id.toString())
                            Text("Umg_url: " + it.img_url)
                        }
                    }
                    Spacer(modifier = Modifier.padding(4.dp))
                }
            }
        }
    }
}
