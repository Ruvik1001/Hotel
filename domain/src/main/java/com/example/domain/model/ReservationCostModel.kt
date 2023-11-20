package com.example.domain.model

/**
 * Data class representing the cost details of a reservation.
 *
 * @property tour_price An [Int] representing the price of the tour.
 * @property fuel_charge An [Int] representing the fuel charge for the reservation.
 * @property service_charge An [Int] representing the service charge for the reservation.
 */
data class ReservationCostModel(
    val tour_price: Int,
    val fuel_charge: Int,
    val service_charge: Int
)
