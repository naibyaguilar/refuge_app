package com.example.refuge.data.db

import android.content.Context
import androidx.databinding.adapters.Converters
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.refuge.data.db.entities.Restroom

@Database(entities = [Restroom::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getRestroomDao(): RestroomDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context)
        }

        private fun buildDatabase(context: Context) {
            Room.databaseBuilder(
                context.applicationContext, AppDatabase::class.java, "MyDatabase.db"
            ).build()

        }

    }
}