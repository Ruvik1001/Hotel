package com.example.domain.repository

import com.example.domain.model.ClientModel

interface ClientRepository {
    /*
        return List< ERROR_STRING_KEY >
     */
    fun validateClient(client: ClientModel): List<String>
    fun postClients(clients: List<ClientModel>): String
}