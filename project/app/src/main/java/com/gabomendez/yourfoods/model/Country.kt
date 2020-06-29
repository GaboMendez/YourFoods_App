package com.gabomendez.yourfoods.model

import com.google.android.gms.maps.model.LatLng

data class Country(
    var code: String,
    var name: String,
    var lat: Double,
    var long: Double,
    var radius: Double,
    var colletion_id: String,
    var city_id: Int = 0
)

object CountryRepo{
    private var countryList = mutableListOf<Country>()
    fun getCountries(): MutableList<Country>{
        var ret = mutableListOf<Country>()
        ret.add(Country(code = "US", name = "United States", lat = 40.11168867, long = -101.86523438, radius = 850000.0, city_id = 280, colletion_id = "1"))
        ret.add(Country(code = "AU", name = "Australia", lat = -23.6445242, long = 133.33007813, radius = 900000.0, city_id = 313, colletion_id = "1"))
        ret.add(Country(code = "CA", name = "Canada", lat = 59.97700549, long = -112.5, radius = 900000.0, city_id = 295, colletion_id = "1"))
        ret.add(Country(code = "BR", name = "Brazil", lat = -10.66060795, long = -51.15234375, radius = 1000000.0, city_id = 66, colletion_id = "1"))
        ret.add(Country(code = "CL", name = "Chile", lat = -26.74561038, long = -69.69726563, radius = 100000.0, city_id = 83, colletion_id = "1"))
        ret.add(Country(code = "IN", name = "India", lat = 22.83694592, long = 78.57421875, radius = 700000.0, city_id = 1, colletion_id = "1"))
        ret.add(Country(code = "IE", name = "Ireland", lat = 52.93539666, long = -8.21777344, radius = 100000.0, city_id = 91, colletion_id = "1"))
        ret.add(Country(code = "IT", name = "Italy", lat = 42.94033923, long = 12.56835938, radius = 200000.0, city_id = 257, colletion_id = "1"))
        ret.add(Country(code = "NZ", name = "New Zealand", lat = -41.88592103, long = 172.37548828, radius = 250000.0, city_id = 71, colletion_id = "1"))
        ret.add(Country(code = "ZA", name = "South Africa", lat = -30.80791068, long = 24.38964844, radius = 500000.0, city_id = 64, colletion_id = "1"))
        ret.add(Country(code = "TR", name = "Turkey", lat = 38.95940879, long = 35.41992188, radius = 350000.0, city_id = 60, colletion_id = "1"))
        ret.add(Country(code = "UK", name = "United Kingdom", lat = 55.02802211, long = -2.37304688, radius = 260000.0, city_id = 68, colletion_id = "1"))

        countryList = ret
        return ret
    }

    fun getCountryByCode(code: String): Country?{
        var ret: Country? = null
        for (country in countryList){
            if (country.code == code)
                ret = country
        }
        return ret
    }

    fun getCountryNameByCode(code: String): String? {
        var ret: String? = null
        for (country in countryList){
            if (country.code == code)
                ret = country.name
        }
        return ret
    }

    fun getLatLngByCode(code: String): LatLng? {
        var ret: LatLng? = null
        for (country in countryList){
            if (country.code == code)
                ret = LatLng(country.lat, country.long)
        }
        return ret
    }
}