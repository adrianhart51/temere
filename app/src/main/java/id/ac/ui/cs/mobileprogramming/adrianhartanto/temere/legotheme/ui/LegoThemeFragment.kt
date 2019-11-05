package id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.legotheme.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.R
import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.data.Result
import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.databinding.FragmentThemesBinding
import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.di.Injectable
import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.di.injectViewModel
import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.ui.VerticalItemDecoration
import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.ui.hide
import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.ui.show
import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.legotheme.ui.LegoThemeAdapter
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class LegoThemeFragment : Fragment(), Injectable {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: LegoThemeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        viewModel = injectViewModel(viewModelFactory)

        val binding = FragmentThemesBinding.inflate(inflater, container, false)
        context ?: return binding.root

        val adapter = LegoThemeAdapter()
        binding.recyclerView.addItemDecoration(
                VerticalItemDecoration(resources.getDimension(R.dimen.margin_normal).toInt(), true) )
        binding.recyclerView.adapter = adapter

        subscribeUi(binding, adapter)

        setHasOptionsMenu(true)
        return binding.root
    }

    private fun subscribeUi(binding: FragmentThemesBinding, adapter: LegoThemeAdapter) {
        viewModel.legoThemes.observe(viewLifecycleOwner, Observer { result ->
            when (result.status) {
                Result.Status.SUCCESS -> {
                    binding.progressBar.hide()
                    result.data?.let { adapter.submitList(it) }
                }
                Result.Status.LOADING -> binding.progressBar.show()
                Result.Status.ERROR -> {
                    binding.progressBar.hide()
                    Snackbar.make(binding.root, result.message!!, Snackbar.LENGTH_LONG).show()
                }
            }
        })
    }
}