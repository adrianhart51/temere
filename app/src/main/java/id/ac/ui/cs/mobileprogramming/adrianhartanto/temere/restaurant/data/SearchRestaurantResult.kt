package id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.restaurant.data

data class SearchRestaurantResult(
    val restaurants: List<RestaurantInfo>,
    val results_found: Int,
    val results_shown: Int,
    val results_start: Int
)