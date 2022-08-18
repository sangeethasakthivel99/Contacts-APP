package com.sangeetha.contacts.data.repository

import com.sangeetha.contacts.data.local.ContactsDao
import com.sangeetha.contacts.data.local.toContact
import com.sangeetha.contacts.data.remote.ContactsService
import com.sangeetha.contacts.data.remote.dto.toContact
import com.sangeetha.contacts.domain.model.toContactEntity
import com.sangeetha.contacts.domain.repository.ContactsRepository
import com.sangeetha.contacts.util.networkBoundResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ContactsRepositoryImpl @Inject constructor(
    private val contactsService: ContactsService,
    private val contactsDb: ContactsDao,
) : ContactsRepository {

    override suspend fun getContacts() = networkBoundResource(
        query = {
            contactsDb.getAllContacts().let {
                it.map { contacts ->
                    contacts.toContact()
                }
            }
        },
        fetch = {
            contactsService.getContacts().let {
                it.map { contacts ->
                    contacts.toContact()
                }
            }
        },
        saveFetchResult = {
            contactsDb.insertContacts(
                it.map { contact ->
                    contact.toContactEntity()
                })
        }
    ).flowOn(Dispatchers.IO)

}