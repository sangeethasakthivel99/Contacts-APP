package com.sangeetha.contacts.domain.repository

import com.sangeetha.contacts.domain.model.Contact
import com.sangeetha.contacts.util.NetworkResource
import kotlinx.coroutines.flow.Flow

interface ContactsRepository {

    suspend fun getContacts(): Flow<NetworkResource<List<Contact>>>
}