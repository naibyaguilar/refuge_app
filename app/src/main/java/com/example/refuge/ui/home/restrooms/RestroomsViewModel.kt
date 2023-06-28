package com.example.refuge.ui.home.restrooms

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.refuge.data.repositories.RestroomRepository
import kotlinx.coroutines.launch

class RestroomsViewModel(repository: RestroomRepository) : ViewModel() {
    val restrooms = repository.getRestroomsFlow()
    init {
        viewModelScope.launch {
            repository.getRestrooms()
        }
    }




}