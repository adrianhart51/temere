package id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.legotheme.data

import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.api.BaseDataSource
import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.api.LegoService
import javax.inject.Inject

/**
 * Works with the Lego API to get data.
 */
class LegoThemeRemoteDataSource @Inject constructor(private val service: LegoService) : BaseDataSource() {

    suspend fun fetchData() = getResult { service.getThemes(1, 1000, "-id") }

}
