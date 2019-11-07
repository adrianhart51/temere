package id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.restaurant.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.databinding.ListItemRestaurantBinding
import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.restaurant.data.Restaurant

/**
 * Adapter for the [RecyclerView] in [RestaurantsFragment].
 */
class RestaurantAdapter : PagedListAdapter<Restaurant, RestaurantAdapter.ViewHolder>(RestauranttDiffCallback()) {

    private lateinit var recyclerView: RecyclerView

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val restaurant = getItem(position)
        restaurant?.let {
            holder.apply {
                bind(createOnClickListener(restaurant.id), restaurant, isGridLayoutManager())
                itemView.tag = restaurant
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ListItemRestaurantBinding.inflate(
                LayoutInflater.from(parent.context), parent, false))
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }

    private fun createOnClickListener(id: String): View.OnClickListener {
        return View.OnClickListener {
            val direction = RestaurantsFragmentDirections.actionToRestaurantDetailFragment(id)
            it.findNavController().navigate(direction)
        }
    }

    private fun isGridLayoutManager() = recyclerView.layoutManager is GridLayoutManager

    class ViewHolder(private val binding: ListItemRestaurantBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(listener: View.OnClickListener, item: Restaurant,
                 isGridLayoutManager: Boolean) {
            binding.apply {
                clickListener = listener
                restaurant = item
                title.visibility = if (isGridLayoutManager) View.GONE else View.VISIBLE
                executePendingBindings()
            }
        }
    }
}

private class RestauranttDiffCallback : DiffUtil.ItemCallback<Restaurant>() {

    override fun areItemsTheSame(oldItem: Restaurant, newItem: Restaurant): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Restaurant, newItem: Restaurant): Boolean {
        return oldItem == newItem
    }
}