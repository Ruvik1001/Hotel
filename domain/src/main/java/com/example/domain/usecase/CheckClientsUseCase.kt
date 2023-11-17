package com.example.domain.usecase

import com.example.domain.model.ClientModel
import com.example.domain.repository.ClientRepository

class CheckClientsUseCase(private val clientRepository: ClientRepository) {
    /*
        return List<Pair< ID_CLIENT, List<PROBLEM_FILED> >>
    */
    fun execute(clients: List<ClientModel>): List<Pair<Int, List<String>>> {
        var problemList = listOf<Pair<Int, List<String>>>()
        for (i in 0 until  clients.size) {
            problemList = problemList.plus(
                Pair(i + 1, clientRepository.validateClient(clients[i]))
            )
        }
        return problemList
    }
}