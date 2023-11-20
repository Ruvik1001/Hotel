package com.example.domain.model

/**
 * Data class representing information about a hotel.
 *
 * @param description A [String] providing a general description of the hotel.
 * @param peculiarities A [List] of [String]s representing peculiarities or special features of the hotel.
 */
data class AboutTheHotel(
    val description: String,
    val peculiarities: List<String>
)
