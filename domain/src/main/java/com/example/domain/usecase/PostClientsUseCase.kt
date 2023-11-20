package com.example.domain.usecase

import com.example.domain.model.ClientModel
import com.example.domain.repository.ClientRepository

/**
 * Use case class for posting client information.
 *
 * @param clientRepository The repository providing access to client-related data and operations.
 */
class PostClientsUseCase(private val clientRepository: ClientRepository) {

    /**
     * Executes the use case to post client information.
     *
     * @param clients The list of clients to be posted.
     * @return A [String] representing the result of the operation (e.g., a ticket number).
     */
    fun execute(clients: List<ClientModel>): String {
        return clientRepository.postClients(clients)
    }
}
