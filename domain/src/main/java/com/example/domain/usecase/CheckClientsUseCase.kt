package com.example.domain.usecase

import com.example.domain.model.ClientModel
import com.example.domain.repository.ClientRepository

class CheckClientsUseCase(private val clientRepository: ClientRepository) {

    fun execute(client: ClientModel): List<String> {
        return clientRepository.validateClient(client)
    }
}