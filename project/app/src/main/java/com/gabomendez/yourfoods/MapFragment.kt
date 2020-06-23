package com.gabomendez.yourfoods

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

                val countries = CountryRepo.getCountries()
                for(country in countries){
                    val market = addMarker(
                        MarkerOptions()
                            .position(LatLng(country.lat, country.long))
                            .title(country.name)
                    )
                    market.tag = country.code

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
                    circle.strokeColor = Color.BLUE
                    circle.fillColor = Color.RED
                    if (circle.tag!! == "A"){
                        println()
                    }
                    onCircleTapped(circle)
                }

            }

        }
    }

    private fun onCircleTapped(circle: Circle?) {
        circle?.let {
            val values = CountryRepo.getCountries()

        }
    }

}
