package id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.legoset.data

import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.api.BaseDataSource
import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.api.LegoService
import javax.inject.Inject

/**
 * Works with the Lego API to get data.
 */
class LegoSetRemoteDataSource @Inject constructor(private val service: LegoService) : BaseDataSource() {

    suspend fun fetchSets(page: Int, pageSize: Int? = null, themeId: Int? = null)
            = getResult { service.getSets(page, pageSize, themeId, "-year") }

    suspend fun fetchSet(id: String)
            = getResult { service.getSet(id) }
}
