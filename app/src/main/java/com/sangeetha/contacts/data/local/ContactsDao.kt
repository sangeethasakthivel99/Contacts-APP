package com.sangeetha.contacts.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactsDao {
    @Query("SELECT * FROM contacts")
    fun getAllContacts(): Flow<List<ContactsEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContacts(restaurants: List<ContactsEntity>)

    @Query("DELETE FROM contacts")
    suspend fun deleteAllContacts()
}