package id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.category.ui.CategoryFragment
import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.legoset.ui.LegoSetFragment
import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.legoset.ui.LegoSetsFragment
import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.legotheme.ui.LegoThemeFragment

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
}
