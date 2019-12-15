package id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.location.ui

import android.Manifest
import android.app.Activity
import android.content.Context
import android.location.Location
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.gms.location.*
import com.google.android.gms.tasks.Task
import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.databinding.FragmentCategoriesBinding
import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.di.Injectable
import com.livinglifetechway.quickpermissions_kotlin.runWithPermissions

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

        val isConnected: Boolean = checkNetworkConnection()
        if (isConnected) {
            getCurrentLocation(categoryId)
        } else {
            val message = "Please turn on your network connection!"
            val duration = Toast.LENGTH_SHORT
            val toast = Toast.makeText(context, message, duration)
            val direction = LocationFragmentDirections.actionToCategoryFragment()

            toast.show()

            findNavController().navigate(direction)
        }

        setHasOptionsMenu(true)
        return binding.root
    }

    fun getCurrentLocation(categoryId: Int) = runWithPermissions(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION) {
        createLocationRequest()

        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                if (location != null) {
                    val direction = LocationFragmentDirections.actionThemeFragmentToRestaurantsFragment(
                        location.latitude.toFloat(), location.longitude.toFloat(), categoryId)
                    findNavController().navigate(direction)
                }
            }
    }

    fun createLocationRequest() {
        val locationRequest = LocationRequest.create()?.apply {
            interval = 10000
            fastestInterval = 5000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }

        val builder = LocationSettingsRequest.Builder()
            .addLocationRequest(locationRequest!!)

        val client: SettingsClient = LocationServices.getSettingsClient(activity as Activity)
        val task: Task<LocationSettingsResponse> = client.checkLocationSettings(builder.build())
    }

    fun checkNetworkConnection() : Boolean {
        val connected: Boolean
        val connectivityManager = context!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val mobileDataConnectionState = connectivityManager!!.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).state
        val wifiConnectionState = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).state

        connected = mobileDataConnectionState == NetworkInfo.State.CONNECTED || wifiConnectionState == NetworkInfo.State.CONNECTED
        return connected
    }
}