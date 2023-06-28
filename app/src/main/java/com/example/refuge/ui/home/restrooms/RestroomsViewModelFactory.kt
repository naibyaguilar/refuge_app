package com.example.refuge.ui.home.restrooms

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.refuge.data.repositories.RestroomRepository

@Suppress("UNCHECKED_CAST")

class RestroomsViewModelFactory(
    private val repository: RestroomRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RestroomsViewModel(repository) as T
    }
}