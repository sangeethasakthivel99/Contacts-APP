package com.sangeetha.contacts.domain.usecase

import com.sangeetha.contacts.domain.repository.ContactsRepository
import javax.inject.Inject

class GetContactsUseCase @Inject constructor(
    private val contactsRepository: ContactsRepository
) {
    operator fun invoke() {

    }
}