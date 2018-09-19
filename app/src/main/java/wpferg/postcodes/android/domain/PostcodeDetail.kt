package wpferg.postcodes.android.domain

import com.google.gson.annotations.SerializedName

data class PostcodeDetail constructor (
    val postcode: String,
    val quality: Int,
    val eastings: String,
    val northings: String,
    val country: String,
    @SerializedName("nhs_ha")
    val nhsHa: String,
    val longitude: String,
    val latitude: String,
    @SerializedName("european_electoral_region")
    val europeanElectoralRegion: String,
    @SerializedName("primary_care_trust")
    val primaryCareTrust: String,
    val region: String,
    val lsoa: String,
    val msoa: String,
    val incode: String,
    val outcode: String,
    @SerializedName("parliamentary_constituency")
    val parliamentaryConstituency: String,
    @SerializedName("admin_district")
    val adminDistrict: String,
    val parish: String,
    @SerializedName("admin_county")
    val adminCounty: String,
    @SerializedName("admin_ward")
    val adminWard: String,
    val ccg: String,
    val nuts: String
)