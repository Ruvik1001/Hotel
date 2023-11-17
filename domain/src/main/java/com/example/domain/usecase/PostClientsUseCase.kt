package com.example.domain.usecase

import com.example.domain.model.ClientModel
import com.example.domain.repository.ClientRepository

class PostClientsUseCase(private val clientRepository: ClientRepository) {
    fun execute(clients: List<ClientModel>): String {
        return clientRepository.postClients(clients)
    }
}