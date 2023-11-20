package com.example.domain.model

/**
 * Data class representing information about a room in a hotel.
 *
 * @property id An [Int] representing the unique identifier of the room.
 * @property name A [String] representing the name of the room.
 * @property price An [Int] representing the price of the room.
 * @property price_per A [String] representing the price per unit associated with the room.
 * @property peculiarities A [List] of [String] representing peculiarities or features of the room.
 * @property image_urls A [List] of [String] representing URLs of images associated with the room.
 */
data class RoomModel(
    val id: Int,
    val name: String,
    val price: Int,
    val price_per: String,
    val peculiarities: List<String>,
    val image_urls: List<String>
)
