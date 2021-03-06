package id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.category.ui.CategoryFragment
import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.legoset.ui.LegoSetFragment
import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.legoset.ui.LegoSetsFragment
import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.legotheme.ui.LegoThemeFragment
import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.location.ui.LocationFragment
import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.restaurant.ui.RestaurantFragment
import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.restaurant.ui.RestaurantsFragment

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeThemeFragment(): LegoThemeFragment

    @ContributesAndroidInjector
    abstract fun contributeLegoSetsFragment(): LegoSetsFragment

    @ContributesAndroidInjector
    abstract fun contributeLegoSetFragment(): LegoSetFragment

    @ContributesAndroidInjector
    abstract fun contributeCategoryFragment(): CategoryFragment

    @ContributesAndroidInjector
    abstract fun contributeRestaurantsFragment(): RestaurantsFragment

    @ContributesAndroidInjector
    abstract fun contributeRestaurantFragment(): RestaurantFragment

    @ContributesAndroidInjector
    abstract fun contributeLocationFragment(): LocationFragment
}
