package com.sangeetha.contacts.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sangeetha.contacts.domain.repository.ContactsRepository
import com.sangeetha.contacts.util.NetworkResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactsViewModel @Inject constructor(
    private val repository: ContactsRepository
) : ViewModel() {

    var state = mutableStateOf(ContactsState())

    init {
        getContacts()
    }

    private fun getContacts() {
        viewModelScope.launch {
            repository.getContacts().collect { result ->
                when (result) {
                    is NetworkResource.Loading -> {
                        state.value = ContactsState(isLoading = true)
                    }

                    is NetworkResource.Success -> {
                        result.data?.let { contacts ->
                            state.value = ContactsState(contacts = contacts)
                        }
                    }

                    is NetworkResource.Failure -> {
                        state.value = ContactsState(error = result.error ?: "Something went wrong")
                    }
                }
            }
        }
    }
}