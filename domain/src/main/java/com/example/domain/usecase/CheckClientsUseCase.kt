package com.example.domain.usecase

import com.example.domain.model.ClientModel
import com.example.domain.repository.ClientRepository

/**
 * Use case class for validating client information.
 *
 * @param clientRepository The repository providing validation logic for client information.
 */
class CheckClientsUseCase(private val clientRepository: ClientRepository) {

    /**
     * Executes the use case to validate client information.
     *
     * @param client The [ClientModel] containing client information to be validated.
     * @return A list of validation results indicating the status of various client information fields.
     */
    fun execute(client: ClientModel): List<String> {
        return clientRepository.validateClient(client)
    }
}
