package id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.location.ui

import android.Manifest
import android.app.Activity
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.R
import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.data.Result
import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.databinding.FragmentCategoriesBinding
import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.di.Injectable
import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.di.injectViewModel
import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.ui.VerticalItemDecoration
import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.ui.hide
import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.ui.show
import com.google.android.material.snackbar.Snackbar
import com.livinglifetechway.quickpermissions_kotlin.runWithPermissions
import javax.inject.Inject

class LocationFragment : Fragment(), Injectable {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val args: LocationFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity as Activity)

        val categoryId : Int = args.categoryId

        val binding = FragmentCategoriesBinding.inflate(inflater, container, false)
        context ?: return binding.root

        getCurrentLocation(categoryId)

        setHasOptionsMenu(true)
        return binding.root
    }

    fun getCurrentLocation(categoryId: Int) = runWithPermissions(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION) {
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                if (location != null) {
                    val direction = LocationFragmentDirections.actionThemeFragmentToRestaurantsFragment(
                        location.latitude.toFloat(), location.longitude.toFloat(), categoryId)
                    findNavController().navigate(direction)
                }
            }
    }
}