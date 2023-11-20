package com.example.domain.repository

import com.example.domain.model.ClientModel

/**
 * Repository interface for client-related operations.
 */
interface ClientRepository {
    /**
     * Validates the provided [ClientModel].
     *
     * @param client The [ClientModel] to be validated.
     * @return A [List] of [String] representing validation results or error keys.
     */
    fun validateClient(client: ClientModel): List<String>

    /**
     * Posts a list of clients.
     *
     * @param clients The list of [ClientModel] to be posted.
     * @return A [String] representing a ticket or identifier for the posted clients.
     */
    fun postClients(clients: List<ClientModel>): String
}
