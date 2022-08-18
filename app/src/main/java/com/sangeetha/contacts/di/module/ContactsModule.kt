package com.sangeetha.contacts.di.module

import android.app.Application
import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sangeetha.contacts.data.local.ContactsDao
import com.sangeetha.contacts.data.local.ContactsDatabase
import com.sangeetha.contacts.data.remote.ContactsService
import com.sangeetha.contacts.data.repository.ContactsRepositoryImpl
import com.sangeetha.contacts.domain.repository.ContactsRepository
import com.sangeetha.contacts.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ContactsModule {
    @Provides
    @Singleton
    fun provideContactsService(): ContactsService {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(ContactsService::class.java)
    }

    @Provides
    @Singleton
    fun provideContactDatabase(application: Application): ContactsDatabase {
        return Room.databaseBuilder(
            application,
            ContactsDatabase::class.java,
            "contacts_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideContactDao(contactsDatabase: ContactsDatabase): ContactsDao {
        return contactsDatabase.contactsDao()
    }

    @Provides
    @Singleton
    fun provideContactRepository(
        contactsService: ContactsService,
        contactsDao: ContactsDao
    ): ContactsRepository {
        return ContactsRepositoryImpl(contactsService, contactsDao)
    }
}