package com.sangeetha.contacts.data.remote.dto

import com.sangeetha.contacts.domain.model.Contact

data class ContactsDto(
    val name: String?,
    val phoneNumber: String?
)

fun ContactsDto.toContact(): Contact {
    return Contact(
        name = this.name,
        phoneNumber = this.phoneNumber
    )
}