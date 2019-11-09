package id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.restaurant.ui

import android.os.Bundle
import android.view.*
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.R
import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.databinding.FragmentRestaurantsBinding
import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.di.Injectable
import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.di.injectViewModel
import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.ui.GridSpacingItemDecoration
import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.ui.VerticalItemDecoration
import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.ui.hide
import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.ui.setTitle
import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.util.ConnectivityUtil
import javax.inject.Inject

class RestaurantsFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: RestaurantsViewModel

    private val args: RestaurantsFragmentArgs by navArgs()

    private lateinit var binding: FragmentRestaurantsBinding
    private val adapter: RestaurantAdapter by lazy { RestaurantAdapter() }
    private lateinit var linearLayoutManager: LinearLayoutManager

    private lateinit var gridLayoutManager: GridLayoutManager
    private val linearDecoration: RecyclerView.ItemDecoration by lazy {
        VerticalItemDecoration(
                resources.getDimension(R.dimen.margin_normal).toInt())
    }
    private val gridDecoration: RecyclerView.ItemDecoration by lazy {
        GridSpacingItemDecoration(
                SPAN_COUNT, resources.getDimension(R.dimen.margin_grid).toInt())
    }

    private var isLinearLayoutManager: Boolean = false

    fun backToCategoryFragment() {
        // This callback will only be called when MyFragment is at least Started.
        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
            // Handle the back button event
            val direction = RestaurantsFragmentDirections.actionToCategoryFragment()
            findNavController().navigate(direction)
        }

        // The callback can be enabled or disabled here or in the lambda
        callback.handleOnBackPressed()
    }

    override fun onDetach() {
        super.onDetach()
        backToCategoryFragment()
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        viewModel = injectViewModel(viewModelFactory)
        viewModel.connectivityAvailable = ConnectivityUtil.isConnected(context!!)
        viewModel.categoryId = if (args.categoryId == -1) null else args.categoryId
        viewModel.latitude = args.latitude!!.toDouble()
        viewModel.longitude = args.longitude!!.toDouble()

        binding = FragmentRestaurantsBinding.inflate(inflater, container, false)
        context ?: return binding.root

        linearLayoutManager = LinearLayoutManager(activity)
        gridLayoutManager = GridLayoutManager(activity, SPAN_COUNT)
        setLayoutManager()
        binding.recyclerView.adapter = adapter

        args.categoryName?.let { setTitle(it) }
        subscribeUi(adapter)

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_list_representation, menu)
        setDataRepresentationIcon(menu.findItem(R.id.list))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.list -> {
                isLinearLayoutManager = !isLinearLayoutManager
                setDataRepresentationIcon(item)
                setLayoutManager()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun subscribeUi(adapter: RestaurantAdapter) {
        viewModel.restaurants.observe(viewLifecycleOwner) {
            binding.progressBar.hide()
            adapter.submitList(it)
        }
    }

    private fun setLayoutManager() {
        val recyclerView = binding.recyclerView

        var scrollPosition = 0
        // If a layout manager has already been set, get current scroll position.
        if (recyclerView.layoutManager != null) {
            scrollPosition = (recyclerView.layoutManager as LinearLayoutManager)
                    .findFirstCompletelyVisibleItemPosition()
        }

        if (isLinearLayoutManager) {
            recyclerView.removeItemDecoration(gridDecoration)
            recyclerView.addItemDecoration(linearDecoration)
            recyclerView.layoutManager = linearLayoutManager
        } else {
            recyclerView.removeItemDecoration(linearDecoration)
            recyclerView.addItemDecoration(gridDecoration)
            recyclerView.layoutManager = gridLayoutManager
        }

        recyclerView.scrollToPosition(scrollPosition)
    }

    private fun setDataRepresentationIcon(item: MenuItem) {
        item.setIcon(if (isLinearLayoutManager)
            R.drawable.ic_grid_list_24dp else R.drawable.ic_list_white_24dp)
    }

    companion object {
        const val SPAN_COUNT = 3
    }

}
