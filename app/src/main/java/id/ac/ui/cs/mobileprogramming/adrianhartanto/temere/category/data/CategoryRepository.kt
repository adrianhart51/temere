package id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.category.data

import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.data.resultLiveData
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository module for handling data operations.
 */
@Singleton
class CategoryRepository @Inject constructor(private val dao: CategoryDao,
                                             private val remoteSource: CategoryRemoteDataSource
) {

    val categories = resultLiveData(
            databaseQuery = { dao.getCategories() },
            networkCall = { remoteSource.fetchData() },
            saveCallResult = { val plants = it.categories.map { c -> c.categories }
                dao.insertAll(plants) })
}
