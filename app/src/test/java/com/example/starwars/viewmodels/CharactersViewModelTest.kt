package com.example.starwars.viewmodels

import com.example.starwars.getOrAwaitValue
import com.example.starwars.model.CharactersResponse
import com.example.starwars.model.Result
import com.example.starwars.repository.StarWarRepository
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class CharactersViewModelTest{

    private val testDispatcher = TestCoroutineDispatcher()
    private lateinit var charactersViewModel : CharactersViewModel

    @Mock
    private lateinit var starWarRepository: StarWarRepository

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(testDispatcher)
        charactersViewModel = CharactersViewModel(starWarRepository)
    }

    @Test
    fun getStarWarCharactersTest(){
        runBlocking {
            Mockito.`when`(starWarRepository.getStarWarCharacters())
                .thenReturn(Response.success(CharactersResponse(5,"","", mutableListOf())))
            charactersViewModel.getStarWarCharacters()
            val result = charactersViewModel.charactersLiveData.getOrAwaitValue()
            assertEquals(CharactersResponse(5,"","", mutableListOf()), result)
        }
    }

}