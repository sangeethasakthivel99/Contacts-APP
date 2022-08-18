package com.sangeetha.contacts.data.remote

import com.sangeetha.contacts.data.remote.dto.ContactsDto
import retrofit2.http.GET

interface ContactsService {

    @GET
    suspend fun getContacts(): List<ContactsDto>

}