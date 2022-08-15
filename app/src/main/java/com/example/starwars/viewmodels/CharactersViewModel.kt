package com.example.starwars.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwars.model.CharactersResponse
import com.example.starwars.repository.Resource
import com.example.starwars.repository.StarWarRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class CharactersViewModel(private val starWarRepository: StarWarRepository) : ViewModel() {

    val charactersLiveData : MutableLiveData<Resource<CharactersResponse>> = MutableLiveData()

    init {
        getStarWarCharacters()
    }

    fun getStarWarCharacters() = viewModelScope.launch {
        charactersLiveData.postValue(Resource.Loading())
        val response = starWarRepository.getStarWarCharacters()
        charactersLiveData.postValue(handleNewsResponse(response))
    }

    private fun handleNewsResponse(response : Response<CharactersResponse>) : Resource<CharactersResponse>{
        if (response.isSuccessful){
            response.body()?.let {
                    result->  return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }
}