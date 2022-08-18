package com.sangeetha.contacts.domain.model

import com.sangeetha.contacts.data.local.ContactsEntity

data class Contact(
    val name: String?,
    val phoneNumber: String?
)


fun Contact.toContactEntity(): ContactsEntity {
    return ContactsEntity(
        name = this.name,
        phoneNumber = this.phoneNumber
    )
}