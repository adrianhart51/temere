package id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.restaurant.data

import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.api.BaseDataSource
import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.api.ZomatoService
import javax.inject.Inject

/**
 * Works with the Lego API to get data.
 */
class RestaurantRemoteDataSource @Inject constructor(private val service: ZomatoService) : BaseDataSource() {

    suspend fun fetchRestaurants(categoryId: Int?, latitude: Double?, longitude: Double?, start: Int = 0, count: Int = 10)
            = getResult { service.searchRestaurants(categoryId, latitude, longitude, start, count) }

    suspend fun fetchRestaurant(restaurantId: Int) = getResult { service.getRestaurant(restaurantId) }
}
