package id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.legotheme.ui

import androidx.lifecycle.ViewModel
import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.legotheme.data.LegoThemeRepository
import javax.inject.Inject

/**
 * The ViewModel for [LegoThemeFragment].
 */
class LegoThemeViewModel @Inject constructor(repository: LegoThemeRepository) : ViewModel() {

    val legoThemes= repository.themes
}
