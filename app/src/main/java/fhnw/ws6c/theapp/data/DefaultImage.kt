package fhnw.ws6c.theapp.data

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.ImageBitmapConfig


fun defaultImage() : ImageBitmap{
    return ImageBitmap(256, 256, ImageBitmapConfig.Alpha8)
}
