package com.example.starwars.viewmodels

import com.example.starwars.repository.StarWarRepository
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock

@RunWith(JUnit4::class)
class CharactersViewModelTest{

    private lateinit var charactersViewModel : CharactersViewModel

    @Mock
    private lateinit var starWarRepository: StarWarRepository

    @Before
    fun setUp(){
        charactersViewModel = CharactersViewModel(starWarRepository)
    }

    @Test
    fun getStarWarCharactersTest(){
        charactersViewModel.getStarWarCharacters()
    }

}