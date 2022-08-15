package com.example.starwars.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.starwars.repository.StarWarRepository

class ViewModelProviderFactory (val starWarRepository: StarWarRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CharactersViewModel(starWarRepository) as T
    }
}