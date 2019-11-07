package id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.restaurant.ui

import androidx.lifecycle.ViewModel
import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.restaurant.data.RestaurantRepository
import javax.inject.Inject

/**
 * The ViewModel used in [RestaurantFragment].
 */
class RestaurantViewModel @Inject constructor(repository: RestaurantRepository) : ViewModel() {

    lateinit var id: String

    val restaurant by lazy { repository.observeRestaurant(id.toInt()) }

}
