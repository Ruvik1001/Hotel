package com.example.domain.model

/**
 * Data class representing an expandable model.
 *
 * @property title A [String] representing the title of the expandable model.
 * @property child A [ClientModel] representing the child model associated with the expandable model.
 */
data class ExpModel(val title: String = "", val child: ClientModel = ClientModel())
