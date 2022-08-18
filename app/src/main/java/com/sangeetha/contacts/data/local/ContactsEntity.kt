package com.sangeetha.contacts.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sangeetha.contacts.domain.model.Contact

@Entity(tableName = "contacts")
data class ContactsEntity(
    @PrimaryKey
    val name: String,
    val phoneNumber: String?
)

fun List<ContactsEntity>.toContact(): List<Contact> {
    return this.map {
        Contact(
            name = it.name,
            phoneNumber = it.phoneNumber
        )
    }
}