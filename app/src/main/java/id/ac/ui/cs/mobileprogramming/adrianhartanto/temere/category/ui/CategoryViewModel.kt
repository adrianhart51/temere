package id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.category.ui

import androidx.lifecycle.ViewModel
import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.category.data.CategoryRepository
import javax.inject.Inject

/**
 * The ViewModel for [CategoryFragment].
 */
class CategoryViewModel @Inject constructor(repository: CategoryRepository) : ViewModel() {

    val categories = repository.categories
}
