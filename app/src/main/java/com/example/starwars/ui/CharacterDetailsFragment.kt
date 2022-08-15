package com.example.starwars.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import com.example.starwars.R
import com.example.starwars.model.Result
import kotlinx.android.synthetic.main.fragment_characters_details_layout.*

class CharacterDetailsFragment : Fragment(R.layout.fragment_characters_details_layout) {

    private val characterDetailsFragmentArgs: CharacterDetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val characterResult : Result = characterDetailsFragmentArgs.result

        tvName.text = characterResult.name
        tvHeight.text = characterResult.height
        tvWeight.text = characterResult.mass
        tvDate.text = characterResult.created


    }
}