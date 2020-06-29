package com.gabomendez.yourfoods.ui.restaurant

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.gabomendez.yourfoods.R
import com.gabomendez.yourfoods.model.CountryRepo
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_map.*


class MapFragment : Fragment(), OnMapReadyCallback {
    private lateinit var googleMap: GoogleMap

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mapView.onCreate(savedInstanceState)
        mapView.onResume()
        mapView.getMapAsync(this)
    }

    override fun onMapReady(map: GoogleMap?) {
        map?.let {
            MapsInitializer.initialize(context)
            googleMap = it
            googleMap.apply {
                val zoomZone = LatLngBounds(LatLng(-23.8858377, -120.234375), LatLng(55.77657302, -40.78125))
                it.moveCamera(CameraUpdateFactory.newLatLngBounds(zoomZone, 0))

                val countries = CountryRepo.getCountries()
                for(country in countries){
                    val marker = addMarker(
                        MarkerOptions()
                            .position(LatLng(country.lat, country.long))
                            .title(country.name)
                    )
                    marker.tag = country.code

                    val circle = addCircle(
                        CircleOptions()
                            .center(LatLng(country.lat, country.long))
                            .radius(country.radius) // In meters
                            .strokeWidth(4F)
                            .strokeColor(Color.parseColor("#434343"))
                            .fillColor(Color.parseColor("#2292000c"))
                    )
                    circle.isClickable = true
                    circle.tag = country.code
                }


                it.setOnCircleClickListener {circle ->
                    val country = CountryRepo.getCountryNameByCode(circle.tag.toString())

                    Toast.makeText(context,"Selected country: $country.",Toast.LENGTH_SHORT).show()
                    val coordinates = CountryRepo.getLatLngByCode(circle.tag.toString())
                    moveToLocation(coordinates!!, circle.tag.toString())

                    Handler().postDelayed({
                        navigateRestaurant(circle.tag.toString())
                    }, 800)
                }

                it.setOnMarkerClickListener { marker ->
                    marker.showInfoWindow()
                    val coordinates = CountryRepo.getLatLngByCode(marker.tag.toString())
                    moveToLocation(coordinates!!, marker.tag.toString())

                    Handler().postDelayed({
                        navigateRestaurant(marker.tag.toString())
                    }, 800)

                    true
                }

            }
        }
    }

    private fun navigateRestaurant(countryCode: String){
        val trans = fragmentManager!!.beginTransaction()
        val restaurantFragment = RestaurantFragment.newInstance(countryCode)
        trans.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)

        trans.replace(R.id.mapContainer, restaurantFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .addToBackStack(null)
            .commitAllowingStateLoss()
    }

    private fun moveToLocation(location: LatLng, countryCode: String) {
        when(countryCode){
            "US" -> zoomMapa(location, 4.7F) //
            "CA" -> zoomMapa(location, 4F) //
            "AU" -> zoomMapa(location, 4f)
            "BR" -> zoomMapa(location, 4.7f) //
            "CL" -> zoomMapa(location, 6.5f) //
            "IN" -> zoomMapa(location, 5f)
            "IE" -> zoomMapa(location, 7f)
            "IT" -> zoomMapa(location, 6.5f) //
            "NZ" -> zoomMapa(location, 6f)
            "ZA" -> zoomMapa(location, 5.6f)
            "TR" -> zoomMapa(location, 5.4f)
            "UK" -> zoomMapa(location, 6f)
        }
    }

    private fun zoomMapa(location: LatLng, zoomValue: Float){
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, zoomValue))
        // Zoom in, animating the camera.
        googleMap.animateCamera(CameraUpdateFactory.zoomIn())
        // Zoom out to zoom level 10, animating with a duration of 5 seconds.
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(zoomValue), 5000, null)
    }

}
