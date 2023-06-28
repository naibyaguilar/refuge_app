package com.example.refuge.data.repositories

import androidx.lifecycle.MutableLiveData
import com.example.refuge.data.db.AppDatabase
import com.example.refuge.data.db.entities.Restroom
import com.example.refuge.data.network.ApiService
import com.example.refuge.data.network.SafeApiRequest
import com.example.refuge.data.preference.PreferenceProvider
import com.example.refuge.util.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

private const val MINIMUM_INTERVAL = 6


class RestroomRepository(
    private val api: ApiService,
    private val db: AppDatabase,
    private val prefs: PreferenceProvider
) : SafeApiRequest() {
    private val restroom = MutableLiveData<List<Restroom>>()

    init {
        restroom.observeForever {
            saveRestroom(it)
        }
    }

    suspend fun getRestrooms()  {
         withContext(Dispatchers.IO){
            fetchRestrooms()

        }
    }

    fun getRestroomsFlow() :Flow<List<Restroom>>{
        return db.getRestroomDao().getRestroom()

    }

    private suspend fun fetchRestrooms(){
        val lastSavedAt = prefs.getSavedAt()
        if (lastSavedAt==null||isFetchNeed(LocalDateTime.parse(lastSavedAt))){
            val quoteResponse = apiRequest { api.getRestrooms() }
            restroom.postValue(quoteResponse)
        }
    }

    private fun isFetchNeed(savedAt: LocalDateTime): Boolean {
        return ChronoUnit.HOURS.between(savedAt,LocalDateTime.now()) >MINIMUM_INTERVAL
    }

    private fun saveRestroom(restroom: List<Restroom>) {
        Coroutines.io {
            prefs.saveSavedAt(LocalDateTime.now().toString())
            db.getRestroomDao().saveAllRestrooms(restroom)
        }
    }


}