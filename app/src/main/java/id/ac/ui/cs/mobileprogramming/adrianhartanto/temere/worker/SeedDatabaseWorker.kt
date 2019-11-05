package id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.legoset.data.LegoSet
import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.util.DATA_FILENAME
import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.data.AppDatabase
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import timber.log.Timber

class SeedDatabaseWorker(
        context: Context,
        workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result = coroutineScope {
        withContext(Dispatchers.IO) {

            try {
                applicationContext.assets.open(DATA_FILENAME).use { inputStream ->
                    JsonReader(inputStream.reader()).use { jsonReader ->
                        val type = object : TypeToken<List<LegoSet>>() {}.type
                        val list: List<LegoSet> = Gson().fromJson(jsonReader, type)

                        AppDatabase.getInstance(applicationContext).legoSetDao().insertAll(list)

                        Result.success()
                    }
                }
            } catch (e: Exception) {
                Timber.e(e, "Error seeding database")
                Result.failure()
            }
        }
    }
}