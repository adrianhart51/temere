package id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.restaurant.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.distinctUntilChanged
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.data.resultLiveData
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository module for handling data operations.
 */
@Singleton
class RestaurantRepository @Inject constructor(private val dao: RestaurantDao,
                                               private val restaurantRemoteDataSource: RestaurantRemoteDataSource) {

    fun observePagedSets(connectivityAvailable: Boolean, categoryId: Int?, latitude: Double?, longitude: Double?,
                         coroutineScope: CoroutineScope) =
            observeRemotePagedSets(categoryId, latitude, longitude, coroutineScope)

    private fun observeRemotePagedSets(categoryId: Int?, latitude: Double?, longitude: Double?, ioCoroutineScope: CoroutineScope)
            : LiveData<PagedList<Restaurant>> {
        val dataSourceFactory = RestaurantPageDataSourceFactory(categoryId, latitude, longitude, restaurantRemoteDataSource,
                dao, ioCoroutineScope)
        return LivePagedListBuilder(dataSourceFactory,
                RestaurantPageDataSourceFactory.pagedListConfig()).build()
    }

    fun observeRestaurant(id: Int) = resultLiveData(
            databaseQuery = { dao.getRestaurant(id) },
            networkCall = { restaurantRemoteDataSource.fetchRestaurant(id) },
            saveCallResult = { dao.insert(it) })
            .distinctUntilChanged()

    companion object {

        const val PAGE_SIZE = 100

        // For Singleton instantiation
        @Volatile
        private var instance: RestaurantRepository? = null

        fun getInstance(dao: RestaurantDao, restaurantRemoteDataSource: RestaurantRemoteDataSource) =
                instance ?: synchronized(this) {
                    instance
                            ?: RestaurantRepository(dao, restaurantRemoteDataSource).also { instance = it }
                }
    }
}
