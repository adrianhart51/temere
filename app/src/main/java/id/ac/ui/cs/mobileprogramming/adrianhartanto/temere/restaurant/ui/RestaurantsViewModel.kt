package id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.restaurant.ui

import androidx.lifecycle.ViewModel
import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.di.CoroutineScropeIO
import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.restaurant.data.RestaurantRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import javax.inject.Inject

/**
 * The ViewModel for [RestaurantsFragment].
 */
class RestaurantsViewModel @Inject constructor(private val repository: RestaurantRepository,
                                               @CoroutineScropeIO private val ioCoroutineScope: CoroutineScope)
    : ViewModel() {

    var connectivityAvailable: Boolean = false
    var categoryId: Int? = null

    val restaurants by lazy {
        repository.observePagedSets(
                connectivityAvailable, categoryId, ioCoroutineScope)
    }

    /**
     * Cancel all coroutines when the ViewModel is cleared.
     */
    override fun onCleared() {
        super.onCleared()
        ioCoroutineScope.cancel()
    }
}
