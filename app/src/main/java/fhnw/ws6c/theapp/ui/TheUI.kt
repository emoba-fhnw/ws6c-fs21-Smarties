package fhnw.ws6c.theapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.darkColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mic
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import fhnw.ws6c.theapp.model.TheModel


@Composable
fun AppUI(model : TheModel){
    with(model){
        Column() {
            Box(contentAlignment = Alignment.Center,
                modifier         = Modifier.fillMaxWidth()
            ){
                Text(text  = title,
                    style = TextStyle(fontSize = 42.sp))
            }
            IconButton(onClick = { recording() }) {
                Icon(imageVector = Icons.Filled.Mic,
                    contentDescription = "",
                    tint = if(isRecording.value){Color.Cyan}else{Color.Black})
            }
            Text(text = printed_text.value, style = TextStyle(fontSize = 32.sp))
            Text(text = audio_text.value, style = TextStyle(fontSize = 32.sp))
        }
    }
}

