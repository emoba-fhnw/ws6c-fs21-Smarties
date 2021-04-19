package fhnw.ws6c.theapp

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import fhnw.ws6c.EmobaApp
import fhnw.ws6c.theapp.data.RemoteCategoryService
import fhnw.ws6c.theapp.model.TheModel
import fhnw.ws6c.theapp.ui.AppUI


object TheApp : EmobaApp {
    lateinit var model : TheModel

    override fun initialize(activity: ComponentActivity) {
        //install service
        val remoteCategoryService = RemoteCategoryService()
        model = TheModel(remoteCategoryService)
    }

    @Composable
    override fun CreateUI() {
        AppUI(model)
    }

}

