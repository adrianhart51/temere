package id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.di

import android.app.Application
import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.api.AuthInterceptor
import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.data.AppDatabase
import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.legotheme.data.LegoThemeRemoteDataSource
import dagger.Module
import dagger.Provides
import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.BuildConfig
import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.api.LegoService
import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.api.ZomatoService
import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.category.data.CategoryRemoteDataSource
import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.legoset.data.LegoSetRemoteDataSource
import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.restaurant.data.RestaurantRemoteDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class, CoreDataModule::class])
class AppModule {

    @Singleton
    @Provides
    fun provideLegoService(@LegoAPI okhttpClient: OkHttpClient,
            converterFactory: GsonConverterFactory
    ) = provideLegoService(okhttpClient, converterFactory, LegoService::class.java)

    @Singleton
    @Provides
    fun provideZomatoService(@ZomatoAPI okhttpClient: OkHttpClient,
                             converterFactory: GsonConverterFactory
    ) = provideZomatoService(okhttpClient, converterFactory, ZomatoService::class.java)

    @Singleton
    @Provides
    fun provideLegoSetRemoteDataSource(legoService: LegoService)
            = LegoSetRemoteDataSource(legoService)

    @Singleton
    @Provides
    fun provideLegoThemeRemoteDataSource(legoService: LegoService)
            = LegoThemeRemoteDataSource(legoService)

    @Singleton
    @Provides
    fun provideCategoryRemoteDataSource(zomatoService: ZomatoService)
            = CategoryRemoteDataSource(zomatoService)

    @Singleton
    @Provides
    fun provideRestaurantRemoteDataSource(zomatoService: ZomatoService)
            = RestaurantRemoteDataSource(zomatoService)

    @LegoAPI
    @Provides
    fun provideLegoApiOkHttpClient(
            upstreamClient: OkHttpClient
    ): OkHttpClient {
        return upstreamClient.newBuilder()
                .addInterceptor(AuthInterceptor(BuildConfig.API_DEVELOPER_TOKEN)).build()
    }

    @ZomatoAPI
    @Provides
    fun provideZomatoApiOkHttpClient(
        upstreamClient: OkHttpClient
    ): OkHttpClient {
        return upstreamClient.newBuilder()
            .addInterceptor(AuthInterceptor(BuildConfig.API_DEVELOPER_TOKEN)).build()
    }

    @Singleton
    @Provides
    fun provideDb(app: Application) = AppDatabase.getInstance(app)

    @Singleton
    @Provides
    fun provideLegoSetDao(db: AppDatabase) = db.legoSetDao()

    @Singleton
    @Provides
    fun provideLegoThemeDao(db: AppDatabase) = db.legoThemeDao()

    @Singleton
    @Provides
    fun provideCategoryDao(db: AppDatabase) = db.categoryDao()

    @Singleton
    @Provides
    fun provideRestaurantDao(db: AppDatabase) = db.restaurantDao()

    @CoroutineScropeIO
    @Provides
    fun provideCoroutineScopeIO() = CoroutineScope(Dispatchers.IO)


    private fun createLegoRetrofitService(
            okhttpClient: OkHttpClient,
            converterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
                .baseUrl(LegoService.ENDPOINT)
                .client(okhttpClient)
                .addConverterFactory(converterFactory)
                .build()
    }

    private fun createZomatoRetrofitService(
        okhttpClient: OkHttpClient,
        converterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ZomatoService.ENDPOINT)
            .client(okhttpClient)
            .addConverterFactory(converterFactory)
            .build()
    }

    private fun <T> provideLegoService(okhttpClient: OkHttpClient,
                                       converterFactory: GsonConverterFactory, clazz: Class<T>): T {
        return createLegoRetrofitService(okhttpClient, converterFactory).create(clazz)
    }

    private fun <T> provideZomatoService(okhttpClient: OkHttpClient,
                                       converterFactory: GsonConverterFactory, clazz: Class<T>): T {
        return createZomatoRetrofitService(okhttpClient, converterFactory).create(clazz)
    }
}
