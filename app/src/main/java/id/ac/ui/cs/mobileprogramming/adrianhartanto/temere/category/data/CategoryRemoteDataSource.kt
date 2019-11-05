package id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.category.data

import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.api.BaseDataSource
import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.api.ZomatoService
import javax.inject.Inject

/**
 * Works with the Lego API to get data.
 */
class CategoryRemoteDataSource @Inject constructor(private val service: ZomatoService) : BaseDataSource() {

    suspend fun fetchData() = getResult { service.getCategories() }

}
