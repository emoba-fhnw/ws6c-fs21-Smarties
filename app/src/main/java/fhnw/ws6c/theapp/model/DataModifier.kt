package fhnw.ws6c.theapp.model

enum class DataModifier(val urlString: String) {
    FilterDrinksByCategory("filter.php?c=Cocktail"),
    SEARCH("search"),
    LookUpDrinkDetailsById("lookup.php?i="),
    RANDOM("random")
}