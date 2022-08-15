package com.example.starwars.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.starwars.R
import com.example.starwars.repository.StarWarRepository
import com.example.starwars.viewmodels.CharactersViewModel
import com.example.starwars.viewmodels.ViewModelProviderFactory

class MainActivity : AppCompatActivity() {

    lateinit var charactersViewModel : CharactersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val starWarRepository = StarWarRepository()
        val viewModelProviderFactory = ViewModelProviderFactory(starWarRepository)
        charactersViewModel = ViewModelProvider(this,viewModelProviderFactory).get(CharactersViewModel::class.java)

        setContentView(R.layout.activity_main)
    }
}