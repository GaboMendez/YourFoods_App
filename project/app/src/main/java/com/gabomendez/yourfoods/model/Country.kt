package com.gabomendez.yourfoods.model

data class Country(
    var countryCode: String,
    var contry: String,
    var lat: Double,
    var long: Double,
    var radius: Double
)

object CountryRepo{
    fun getCountries(): MutableList<Country>{
        var ret = mutableListOf<Country>()
        ret.add(Country(countryCode = "US", contry = "United States", lat = 39.9097, long = -101.9531, radius = 500000.0))
        ret.add(Country(countryCode = "AW", contry = "Aruba", lat = 12.5123, long = -69.9801, radius = 500000.0))
        ret.add(Country(countryCode = "CA", contry = "Canada", lat = 57.0407, long = -107.4023, radius = 500000.0))
        ret.add(Country(countryCode = "GP", contry = "Guadalupe", lat = 16.1640, long = -61.6808, radius = 500000.0))
        ret.add(Country(countryCode = "HK", contry = "Hong Kong", lat = 22.4259, long = 114.1251, radius = 500000.0))
        ret.add(Country(countryCode = "KN", contry = "Saint Kitts and Nevis", lat = 17.3541, long = -62.7846, radius = 500000.0))
        ret.add(Country(countryCode = "KY", contry = "Cayman Islands", lat = 19.3198, long = -81.2428, radius = 500000.0))
        ret.add(Country(countryCode = "MX", contry = "Mexico", lat = 19.3526, long = -459.2065, radius = 500000.0))
        ret.add(Country(countryCode = "SV", contry = "El Salvador", lat = 13.6353, long = -459.2065, radius = 500000.0))
        ret.add(Country(countryCode = "VI", contry = "U.S. Virgin Islands", lat = 17.7291, long = -64.7843, radius = 500000.0))
        return ret
    }
}