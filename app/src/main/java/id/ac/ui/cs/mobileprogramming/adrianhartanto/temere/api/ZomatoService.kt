package id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.api

import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.restaurant.data.Restaurant
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Zomato REST API access points
 */
interface ZomatoService {

    companion object {
        const val ENDPOINT = "https://developers.zomato.com/api/"
    }

    @GET("v2.1/categories")
    suspend fun getCategories(): Response<CategoriesResponse>

    @GET("v2.1/search")
    suspend fun searchRestaurants(
        @Query("category") categoryId: Int?,
        @Query("lat") latitude: Double = -6.595038,
        @Query("lon") longitude: Double = 106.816635,
        @Query("start") start: Int = 0,
        @Query("count") count: Int = 10,
        @Query("sort") sort: String = "real_distance",
        @Query("order") order: String = "asc"
    ): Response<SearchRestaurantsResponse>

    @GET("v2.1/restaurant")
    suspend fun getRestaurant(@Query("res_id") restaurantId: Int): Response<Restaurant>
}