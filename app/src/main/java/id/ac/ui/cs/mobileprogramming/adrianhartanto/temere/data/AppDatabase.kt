package id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.category.data.Category
import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.category.data.CategoryDao
import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.legoset.data.LegoSet
import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.legotheme.data.LegoTheme
import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.legotheme.data.LegoThemeDao
import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.legoset.data.LegoSetDao
import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.worker.SeedDatabaseWorker

/**
 * The Room database for this app
 */
@Database(entities = [LegoTheme::class,
    LegoSet::class, Category::class],
        version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun legoSetDao(): LegoSetDao

    abstract fun legoThemeDao(): LegoThemeDao

    abstract fun categoryDao(): CategoryDao

    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        // Create and pre-populate the database. See this article for more details:
        // https://medium.com/google-developers/7-pro-tips-for-room-fbadea4bfbd1#4785
        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, "temere-db")
                    .addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            val request = OneTimeWorkRequestBuilder<SeedDatabaseWorker>().build()
                            WorkManager.getInstance(context).enqueue(request)
                        }
                    })
                    .build()
        }
    }
}
