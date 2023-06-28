package com.example.refuge.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.refuge.data.db.entities.Restroom
import kotlinx.coroutines.flow.Flow

@Dao
interface RestroomDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllRestrooms(quotes: List<Restroom>)

    @Query("SELECT * FROM restroom")
    fun getRestroom(): Flow<List<Restroom>>
}