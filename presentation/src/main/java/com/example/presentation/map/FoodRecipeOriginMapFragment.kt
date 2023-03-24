package com.example.presentation.map

import android.os.Bundle
import android.view.View
import com.example.common.presentation.extensions.getStringOrDefault
import com.example.presentation.BaseFragment
import com.example.presentation.databinding.FragmentFoodRecipeOriginMapBinding
import com.example.presentation.details.FoodRecipeDetailsFragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FoodRecipeOriginMapFragment :
    BaseFragment<FragmentFoodRecipeOriginMapBinding>(FragmentFoodRecipeOriginMapBinding::inflate),
    OnMapReadyCallback {

    private var mGoogleMap: GoogleMap? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initMap()
    }

    override fun onMapReady(p0: GoogleMap) {
        mGoogleMap = p0
        val latLong = LatLng(getLatitude(), getLongitude())
        mGoogleMap?.run {
            uiSettings.isZoomControlsEnabled = true
            addMarker(MarkerOptions().position(latLong).title(getFoodRecipeName()))
            moveCamera(CameraUpdateFactory.newLatLng(latLong))
            animateCamera(CameraUpdateFactory.zoomTo(ZOOM), DURATION, null)
        }
    }

    private fun initMap() {
        binding.googleMap.getFragment<SupportMapFragment>().getMapAsync(this)
    }

    private fun getFoodRecipeName() = getStringOrDefault(FoodRecipeDetailsFragment.FOOD_RECIPE_NAME)

    private fun getLatitude() = arguments?.getString(LATITUDE)?.toDouble() ?: 0.0

    private fun getLongitude() = arguments?.getString(LONGITUDE)?.toDouble() ?: 0.0

    override fun onDestroy() {
        super.onDestroy()
        mGoogleMap = null
    }
    companion object {
        private const val LATITUDE = "latitude"
        private const val LONGITUDE = "longitude"
        private const val ZOOM = 7F
        private const val DURATION = 2000
    }
}
