package com.example.starwars.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.starwars.R
import com.example.starwars.adapter.CharactersAdapter
import com.example.starwars.repository.Resource
import com.example.starwars.utils.Constants
import com.example.starwars.viewmodels.CharactersViewModel
import kotlinx.android.synthetic.main.fragment_characters_layout.*

class CharactersFragment : Fragment(R.layout.fragment_characters_layout) {

    private lateinit var viewModel: CharactersViewModel
    private lateinit var charactersAdapter: CharactersAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as MainActivity).charactersViewModel

        setUpNewsAdapter()

        viewModel.charactersLiveData.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgress()
                    response.data?.let {
                        charactersAdapter.differ.submitList(it.results)
                    }

                }
                is Resource.Error -> {
                    hideProgress()
                    response.message?.let {
                        Log.e("news error", it)
                        Toast.makeText(activity,it,Toast.LENGTH_LONG).show()
                    }
                    tvErrorInfo.visibility = View.VISIBLE
                    btnTryAgain.visibility = View.VISIBLE

                }
                is Resource.Loading -> {
                    showProgress()
                }
                else -> {}
            }
        }

        btnTryAgain.setOnClickListener { viewModel.getStarWarCharacters() }
    }

    private fun setUpNewsAdapter() {
        charactersAdapter = CharactersAdapter()
        rvCharacters.apply {
            adapter = charactersAdapter
            layoutManager = LinearLayoutManager(activity)

        }
        charactersAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable(Constants.RESULT,it)
            }
            findNavController().navigate(R.id.action_charactersFragment_to_characterDetailsFragment,bundle)
        }
    }

    private fun hideProgress() {
        progressBar.visibility = View.INVISIBLE
        tvErrorInfo.visibility = View.INVISIBLE
        btnTryAgain.visibility = View.INVISIBLE
    }

    private fun showProgress() {
        progressBar.visibility = View.VISIBLE
        tvErrorInfo.visibility = View.INVISIBLE
        btnTryAgain.visibility = View.INVISIBLE
    }
}