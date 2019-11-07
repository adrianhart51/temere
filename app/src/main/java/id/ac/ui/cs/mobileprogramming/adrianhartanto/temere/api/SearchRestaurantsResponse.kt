package id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.api

import com.google.gson.annotations.SerializedName
import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.restaurant.data.RestaurantInfo

data class SearchRestaurantsResponse(
    @SerializedName("results_found")
    val results_found: Int,
    @SerializedName("results_start")
    val results_start: Int,
    @SerializedName("results_shown")
    val results_shown: Int,
    @SerializedName("restaurants")
    val restaurants: List<RestaurantInfo>
)