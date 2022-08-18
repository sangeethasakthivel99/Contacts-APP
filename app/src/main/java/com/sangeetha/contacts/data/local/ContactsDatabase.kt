package com.sangeetha.contacts.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ContactsEntity::class], version = 1, exportSchema = false)
abstract class ContactsDatabase: RoomDatabase() {
    abstract fun contactsDao(): ContactsDao
}