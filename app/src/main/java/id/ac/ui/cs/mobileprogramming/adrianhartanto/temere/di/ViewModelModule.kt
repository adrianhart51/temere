package id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.category.ui.CategoryViewModel
import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.legoset.ui.LegoSetViewModel
import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.legoset.ui.LegoSetsViewModel
import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.legotheme.ui.LegoThemeViewModel

@Suppress("unused")
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(LegoThemeViewModel::class)
    abstract fun bindThemeViewModel(viewModel: LegoThemeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LegoSetsViewModel::class)
    abstract fun bindLegoSetsViewModel(viewModel: LegoSetsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LegoSetViewModel::class)
    abstract fun bindLegoSetViewModel(viewModel: LegoSetViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CategoryViewModel::class)
    abstract fun bindCategoryViewModel(viewModel: CategoryViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
