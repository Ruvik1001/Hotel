package com.example.domain.model

/**
 * Data class representing information about a reservation tour.
 *
 * @property hotel_name A [String] representing the name of the hotel for the reservation.
 * @property departure A [String] representing the departure information for the tour.
 * @property arrival_country A [String] representing the country of arrival for the tour.
 * @property tour_date_start A [String] representing the start date of the tour.
 * @property tour_date_stop A [String] representing the end date of the tour.
 * @property number_of_nights An [Int] representing the number of nights for the tour.
 * @property room A [String] representing information about the room for the reservation.
 * @property nutrition A [String] representing information about the nutrition for the tour.
 */
data class ReservationTourModel(
    val hotel_name: String,
    val departure: String,
    val arrival_country: String,
    val tour_date_start: String,
    val tour_date_stop: String,
    val number_of_nights: Int,
    val room: String,
    val nutrition: String
)