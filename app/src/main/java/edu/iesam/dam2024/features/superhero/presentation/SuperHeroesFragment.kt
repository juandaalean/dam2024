package edu.iesam.dam2024.features.superhero.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.iesam.dam2024.databinding.FragmentSuperheroesBinding
import edu.iesam.dam2024.features.superhero.domain.SuperHero


class SuperHeroesFragment: Fragment() {

    private lateinit var factory: SuperHeroFactory
    private lateinit var viewModel: SuperHeroesViewModel

    private var _binding: FragmentSuperheroesBinding? = null
    private val binding get() = _binding!!

    private val superHeroAdapter = SuperHeroAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSuperheroesBinding.inflate(inflater, container, false)
        setupView()
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        factory = SuperHeroFactory(requireContext())
        viewModel = factory.buildViewModel()
        viewModel.loadSuperHeroes()
        setupObserver()
    }

    private fun setupObserver() {
        val observer = Observer<SuperHeroesViewModel.UiState> { uiState ->
            //código de respuesta
            uiState.superHeroes?.let { superHeroes ->
                superHeroAdapter.submitList(superHeroes)
            }

            uiState.errorApp?.let {
                //Pinto el error
            } ?: run {
                //Ocultar el error
            }

            if (uiState.isLoading) {
                //Muestro cargando...
            } else {
                //Oculto el cargando...
            }
        }
        viewModel.uiState.observe(viewLifecycleOwner, observer)
    }

    private fun setupView() {
        binding.apply {
            superHeroesRecyclerView.layoutManager = LinearLayoutManager(
                requireContext(),
                RecyclerView.VERTICAL,
                false)
            superHeroesRecyclerView.adapter = superHeroAdapter
        }
    }


    /*
    private fun navigateToDetails(superheroId: String){
        findNavController().navigate(SuperHeroesFragmentDirections.actionFromSuperheroToSuperheroDetail(superheroId))
    }

     */
}