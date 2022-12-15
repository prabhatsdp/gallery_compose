package dev.prabhatpandey.gallerycompose.core.data.dto.image


import com.google.gson.annotations.SerializedName

data class Sponsorship(
    @SerializedName("impression_urls")
    val impressionUrls: List<String>,
    @SerializedName("sponsor")
    val sponsor: Sponsor,
    @SerializedName("tagline")
    val tagline: String,
    @SerializedName("tagline_url")
    val taglineUrl: String
)