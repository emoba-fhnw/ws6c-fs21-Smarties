package fhnw.ws6c.theapp.model

import fhnw.ws6c.theapp.TheApp.model

enum class DataModifier(val urlString: String) {
    FilterDrinksByCategory("filter.php?c=Cocktail"),
    SEARCH("search"),
    LookUpDrinkDetailsById("lookup.php?i=" + model.currentDrink.id),
    RANDOM("random")
}