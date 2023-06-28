package com.example.refuge

import android.app.Application
import androidx.room.Room
import com.example.refuge.data.db.AppDatabase
import com.example.refuge.data.network.ApiService
import com.example.refuge.data.network.NetworkConnectionInterceptor
import com.example.refuge.data.preference.PreferenceProvider
import com.example.refuge.data.repositories.RestroomRepository
import com.example.refuge.ui.home.restrooms.RestroomsViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class MVVMApplication() : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@MVVMApplication))
        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { ApiService(instance()) }
        bind<AppDatabase>() with singleton {
            Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java,
                "my-database"
            ).build()
        }
        bind() from singleton { PreferenceProvider(instance()) }
        bind() from singleton { RestroomRepository(instance(), instance(), instance()) }
        bind() from provider { RestroomsViewModelFactory(instance()) }

    }
}