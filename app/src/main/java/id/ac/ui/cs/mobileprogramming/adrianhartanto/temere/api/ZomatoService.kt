package id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.api

import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.category.data.Categories
import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.category.data.Category
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
    suspend fun getCategories(): Response<ZomatoResponse<Categories>>

    @GET("v2.1/cuisines")
    suspend fun getCuisines(@Query("lat") latitude: Double,
                    @Query("lon") longitude: Double): Response<ResultsResponse<Category>>

    @GET("v2.1/search")
    suspend fun searchRestaurants(@Query("lat") latitude: Double,
                          @Query("lon") longitude: Double,
                          @Query("cuisines") cuisinesId: Int,
                          @Query("sort") sort: String = "real_distance",
                          @Query("order") order: String = "asc"): Response<ResultsResponse<Category>>
}