package id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.restaurant.data

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.restaurant.data.Restaurant

/**
 * The Data Access Object for the Restaurant class.
 */
@Dao
interface RestaurantDao {

    @Query("SELECT * FROM restaurant")
    fun getRestaurants(): LiveData<List<Restaurant>>

    @Query("SELECT * FROM restaurant")
    fun getPagedRestaurants(): DataSource.Factory<Int, Restaurant>

    @Query("SELECT * FROM restaurant WHERE id = :id")
    fun getRestaurant(id: Int): LiveData<Restaurant>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(restaurants: List<Restaurant>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(restaurant: Restaurant)
}
