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
                val sydney = LatLng(-33.852, 151.211)
                addMarker(
                    MarkerOptions()
                        .position(sydney)
                        .title("Marker in Sydney")
                )

                val countries = CountryRepo.getCountries()
                for(country in countries){
                    val circle = addCircle(
                        CircleOptions()
                            .center(LatLng(country.lat, country.long))
                            .radius(country.radius) // In meters
                            .strokeWidth(4F)
                            .strokeColor(Color.parseColor("#434343"))
                            .fillColor(Color.parseColor("#22571205"))
                    )
                    circle.isClickable = true
                    circle.tag = country.countryCode
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
