package com.example.domain.model

/**
 * Data class representing a hotel model.
 *
 * @property id An [Int] representing the unique identifier of the hotel.
 * @property name A [String] representing the name of the hotel.
 * @property adress A [String] representing the address of the hotel.
 * @property minimal_price An [Int] representing the minimal price for the hotel.
 * @property price_for_it A [String] representing the price for the hotel.
 * @property rating An [Int] representing the rating of the hotel.
 * @property rating_name A [String] representing the name associated with the hotel's rating.
 * @property image_urls A [List] of [String] representing the URLs of the hotel's images.
 * @property about_the_hotel An [AboutTheHotel] object containing additional information about the hotel.
 */
data class HotelModel(
    val id: Int,
    val name: String,
    val adress: String,
    val minimal_price: Int,
    val price_for_it: String,
    val rating: Int,
    val rating_name: String,
    val image_urls: List<String>,
    val about_the_hotel: AboutTheHotel
)