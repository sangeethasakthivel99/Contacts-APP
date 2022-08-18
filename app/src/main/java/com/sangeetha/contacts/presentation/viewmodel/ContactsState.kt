package com.sangeetha.contacts.presentation.viewmodel

import com.sangeetha.contacts.domain.model.Contact

data class ContactsState(
    val isLoading: Boolean = false,
    val contacts: List<Contact> = emptyList(),
    val error: String = ""
)