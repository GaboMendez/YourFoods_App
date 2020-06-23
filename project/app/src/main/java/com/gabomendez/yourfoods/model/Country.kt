package com.gabomendez.yourfoods.model

data class Country(
    var code: String,
    var name: String,
    var lat: Double,
    var long: Double,
    var radius: Double
)

object CountryRepo{
    fun getCountries(): MutableList<Country>{
        var ret = mutableListOf<Country>()
        ret.add(Country(code = "US", name = "United States", lat = 40.11168867, long = -101.86523438, radius = 850000.0))
        ret.add(Country(code = "AW", name = "Aruba", lat = 12.51551982, long = -69.98145103, radius = 15000.0))
        ret.add(Country(code = "CA", name = "Canada", lat = 59.97700549, long = -112.5, radius = 900000.0))
        ret.add(Country(code = "GP", name = "Guadalupe", lat = 16.24631999, long = -61.5838623, radius = 40000.0))
        ret.add(Country(code = "HK", name = "Hong Kong", lat = 22.36150647, long = 114.13009644, radius = 20000.0))
        ret.add(Country(code = "KN", name = "Saint Kitts and Nevis", lat = 17.31983192, long = 297.2574234, radius = 15000.0))
        ret.add(Country(code = "KY", name = "Cayman Islands", lat = 19.32021528, long = 278.75198364, radius = 15000.0))
        ret.add(Country(code = "MX", name = "Mexico", lat = 23.07973176, long = -102.12890625, radius = 450000.0))
        ret.add(Country(code = "SV", name = "Salvador", lat = 13.60327813, long = -88.79150391, radius = 80000.0))
        ret.add(Country(code = "VI", name = "U.S. Virgin Islands", lat = 17.73233683, long = 295.23593903, radius = 15000.0))
        return ret
    }

    fun getCountryByCode(code: String): String{
        return when(code){
            "US" -> "United States"
            "AW" -> "Aruba"
            "CA" -> "Canada"
            "GP" -> "Guadalupe"
            "HK" -> "Hong Kong"
            "KN" -> "Saint Kitts and Nevis"
            "KY" -> "Cayman Islands"
            "MX" -> "Mexico"
            "SV" -> "Salvador"
            "VI" -> "U.S. Virgin Islands"
            else -> "Not Found..."
        }
    }
}