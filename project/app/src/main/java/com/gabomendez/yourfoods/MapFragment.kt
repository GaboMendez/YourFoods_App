package com.gabomendez.yourfoods

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.gabomendez.yourfoods.model.*
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.*
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
                val zoomZone = LatLngBounds(LatLng(-20.63278425, 233.4375), LatLng(71.30079292, 298.125))
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
                    val country = CountryRepo.getCountryByCode(circle.tag.toString())
                    Toast.makeText(context,"Selected country: $country.",Toast.LENGTH_SHORT).show()
                }

                it.setOnMarkerClickListener { marker ->
                    marker.showInfoWindow()
                    true
                }

            }

        }
    }

}
