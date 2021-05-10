package fhnw.ws6c.theapp

import androidx.activity.ComponentActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import fhnw.ws6c.EmobaApp
import fhnw.ws6c.theapp.data.services.RemoteRequestService
import fhnw.ws6c.theapp.data.services.RemoteImageService
import fhnw.ws6c.theapp.model.CocktailModel
import fhnw.ws6c.theapp.ui.AppUI


class TheApp(var context : ComponentActivity) : EmobaApp {
    lateinit var model : CocktailModel

    override fun initialize(activity: ComponentActivity) {
        //install service
        val remoteCategoryService = RemoteRequestService()
        val remoteImageService = RemoteImageService()
        model = CocktailModel(remoteCategoryService, remoteImageService, context)
    }

    @ExperimentalFoundationApi
    @Composable
    override fun CreateUI() {
        AppUI(model)
    }
}

