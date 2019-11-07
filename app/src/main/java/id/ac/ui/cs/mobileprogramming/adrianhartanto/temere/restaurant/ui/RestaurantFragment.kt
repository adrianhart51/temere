package id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.restaurant.ui

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.R
import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.binding.bindImageFromUrl
import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.data.Result
import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.databinding.FragmentRestaurantBinding
import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.di.Injectable
import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.di.injectViewModel
import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.restaurant.data.Restaurant
import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.ui.hide
import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.ui.setTitle
import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.ui.show
import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.util.intentOpenWebsite
import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.util.intentShareText
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

/**
 * A fragment representing a single Restaurant detail screen.
 */
class RestaurantFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: RestaurantViewModel

    private val args: RestaurantFragmentArgs by navArgs()
    private lateinit var restaurantData: Restaurant

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        viewModel = injectViewModel(viewModelFactory)
        viewModel.id = args.id

        val binding = DataBindingUtil.inflate<FragmentRestaurantBinding>(
                inflater, R.layout.fragment_restaurant, container, false).apply {
            lifecycleOwner = this@RestaurantFragment
            fab.setOnClickListener { _ -> restaurantData.url?.let { intentOpenWebsite(activity!!,it) } }
        }

        subscribeUi(binding)

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_share, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    @Suppress("DEPRECATION")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_share -> {
                intentShareText(activity!!, getString(R.string.share_restaurant, restaurantData.name, restaurantData.url ?: ""))
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun subscribeUi(binding: FragmentRestaurantBinding) {
        viewModel.restaurant.observe(viewLifecycleOwner, Observer { result ->
            when (result.status) {
                Result.Status.SUCCESS -> {
                    binding.progressBar.hide()
                    result.data?.let { bindView(binding, it) }
                }
                Result.Status.LOADING -> binding.progressBar.show()
                Result.Status.ERROR -> {
                    binding.progressBar.hide()
                    Snackbar.make(binding.coordinatorLayout, result.message!!, Snackbar.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun bindView(binding: FragmentRestaurantBinding, restaurant: Restaurant) {
        restaurant.apply {
            setTitle(name)
            bindImageFromUrl(binding.image, featured_image)
            binding.name.text = name
            restaurantData = restaurant
        }
    }
}
