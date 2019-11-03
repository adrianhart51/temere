package id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.category.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * The Data Access Object for the Category class.
 */
@Dao
interface CategoryDao {

    @Query("SELECT * FROM category ORDER BY category_id DESC")
    fun getCategories(): LiveData<List<Category>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(plants: List<Category>)
}
