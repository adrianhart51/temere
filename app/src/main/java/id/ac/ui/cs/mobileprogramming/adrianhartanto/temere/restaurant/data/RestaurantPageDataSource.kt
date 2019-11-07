package id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.restaurant.data

import androidx.paging.PageKeyedDataSource
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber
import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.data.Result
import javax.inject.Inject

/**
 * Data source for restaurant pagination via paging library
 */
class RestaurantPageDataSource @Inject constructor(
    private val categoryId: Int?,
    private val latitude: Double,
    private val longitude: Double,
    private val dataSource: RestaurantRemoteDataSource,
    private val dao: RestaurantDao,
    private val scope: CoroutineScope) : PageKeyedDataSource<Int, Restaurant>() {

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Restaurant>) {
        fetchData(1, params.requestedLoadSize) {
            callback.onResult(it, null, 2)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Restaurant>) {
        val page = params.key
        fetchData(page, params.requestedLoadSize) {
            callback.onResult(it, page + 1)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Restaurant>) {
        val page = params.key
        fetchData(page, params.requestedLoadSize) {
            callback.onResult(it, page - 1)
        }
    }

    private fun fetchData(start: Int, count: Int, callback: (List<Restaurant>) -> Unit) {
        scope.launch(getJobErrorHandler()) {
            val response = dataSource.fetchRestaurants(categoryId, latitude, longitude, start, count)
            if (response.status == Result.Status.SUCCESS) {
                val results = response.data!!.restaurants.map { restaurantInfo -> restaurantInfo.restaurant }
                dao.insertAll(results)
                callback(results)
            } else if (response.status == Result.Status.ERROR) {
                postError(response.message!!)
            }
        }
    }

    private fun getJobErrorHandler() = CoroutineExceptionHandler { _, e ->
        postError(e.message ?: e.toString())
    }

    private fun postError(message: String) {
        Timber.e("An error happened: $message")
        // TODO network error handling
        //networkState.postValue(NetworkState.FAILED)
    }

}