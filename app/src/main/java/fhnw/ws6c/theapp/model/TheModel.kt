package fhnw.ws6c.theapp.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import fhnw.ws6c.theapp.data.Category
import fhnw.ws6c.theapp.data.RemoteCategoryService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class TheModel(val remoteCategoryService: RemoteCategoryService) {
    val title = "Hello ws6C"
    var isLoading       by mutableStateOf(false)

    private val modelScope = CoroutineScope(SupervisorJob() + Dispatchers.IO) //for loding data asynchrounously

    val category_model = CategoryModel()


    fun loadChoosenCategoryAsync(choosenCategory : Category){
        isLoading = true;
        modelScope.launch{
            loadChoosenCategory()
        }
        isLoading = false
    }

    private fun loadChoosenCategory(){

    }
}