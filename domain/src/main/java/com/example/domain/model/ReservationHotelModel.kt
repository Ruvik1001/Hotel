package com.example.domain.model

/**
 * Data class representing information about the hotel in a reservation.
 *
 * @property hotel_name A [String] representing the name of the hotel.
 * @property hotel_adress A [String] representing the address of the hotel.
 * @property hotel_rating An [Int] representing the rating of the hotel.
 * @property rating_name A [String] representing the name associated with the hotel's rating.
 */
data class ReservationHotelModel(
    val hotel_name: String,
    val hotel_adress: String,
    val hotel_rating: Int,
    val rating_name: String
)
