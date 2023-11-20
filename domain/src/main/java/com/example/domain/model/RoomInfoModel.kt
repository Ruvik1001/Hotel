package com.example.domain.model

/**
 * Data class representing information about room details in a hotel.
 *
 * @property id An [Int] representing the unique identifier of the room.
 * @property hotel_name A [String] representing the name of the hotel associated with the room.
 * @property hotel_adress A [String] representing the address of the hotel associated with the room.
 * @property horating An [Int] representing the rating of the hotel associated with the room.
 * @property rating_name A [String] representing the name associated with the hotel's rating.
 * @property departure A [String] representing the departure information for the tour.
 * @property arrival_country A [String] representing the country of arrival for the tour.
 * @property tour_date_start A [String] representing the start date of the tour.
 * @property tour_date_stop A [String] representing the end date of the tour.
 * @property number_of_nights An [Int] representing the number of nights for the tour.
 * @property room A [String] representing information about the room.
 * @property nutrition A [String] representing information about the nutrition for the tour.
 * @property tour_price An [Int] representing the price of the tour.
 * @property fuel_charge An [Int] representing the fuel charge for the tour.
 * @property service_charge An [Int] representing the service charge for the tour.
 */
data class RoomInfoModel(
    val id: Int,
    val hotel_name: String,
    val hotel_adress: String,
    val horating: Int,
    val rating_name: String,
    val departure: String,
    val arrival_country: String,
    val tour_date_start: String,
    val tour_date_stop: String,
    val number_of_nights: Int,
    val room: String,
    val nutrition: String,
    val tour_price: Int,
    val fuel_charge: Int,
    val service_charge: Int
)

