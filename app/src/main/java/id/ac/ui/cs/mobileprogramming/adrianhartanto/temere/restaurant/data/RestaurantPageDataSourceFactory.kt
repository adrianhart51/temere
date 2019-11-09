package id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.restaurant.data

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class RestaurantPageDataSourceFactory @Inject constructor(
    private val categoryId: Int?,
    private val latitude: Double?,
    private val longitude: Double?,
    private val dataSource: RestaurantRemoteDataSource,
    private val dao: RestaurantDao,
    private val scope: CoroutineScope) : DataSource.Factory<Int, Restaurant>() {

    private val liveData = MutableLiveData<RestaurantPageDataSource>()

    override fun create(): DataSource<Int, Restaurant> {
        val source = RestaurantPageDataSource(categoryId, latitude, longitude, dataSource, dao, scope)
        liveData.postValue(source)
        return source
    }

    companion object {
        private const val PAGE_SIZE = 100

        fun pagedListConfig() = PagedList.Config.Builder()
                .setInitialLoadSizeHint(PAGE_SIZE)
                .setPageSize(PAGE_SIZE)
                .setEnablePlaceholders(true)
                .build()
    }

}