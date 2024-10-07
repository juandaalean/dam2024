package edu.iesam.dam2024.features.superhero.presentation

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import edu.iesam.dam2024.R
import edu.iesam.dam2024.features.superhero.domain.SuperHero

class SuperHeroesActivity : AppCompatActivity() {

    private lateinit var  superHeroFactory: SuperHeroFactory
    private lateinit var viewModel: SuperHeroesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_superhero)

        superHeroFactory = SuperHeroFactory(this)
        viewModel = superHeroFactory.buildViewModel()
        setupObserver()
        viewModel.viewCreated()
    }

    private fun setupObserver(){
        val superHeroObserver = Observer<SuperHeroesViewModel.UiState>{ uiState ->
            uiState.superheroes?.let{
                bindData(it)
            }
            uiState.errorApp?.let{
                //Contenido
            }
            if(uiState.isLoading){
                //Contenido
            } else{
                //Contenido
            }
        }
        viewModel.uiState.observe(this, superHeroObserver)
    }

    private fun bindData(superHeroes: List<SuperHero>) {
        findViewById<TextView>(R.id.superhero_id_1).text = superHeroes[0].id
        findViewById<TextView>(R.id.superhero_name_1).text = superHeroes[0].name
        findViewById<LinearLayout>(R.id.layout_1).setOnClickListener {
            navegateToSuperHeroDetail(superHeroes[0].id)
        }

        findViewById<TextView>(R.id.superhero_id_2).text = superHeroes[1].id
        findViewById<TextView>(R.id.superhero_name_2).text = superHeroes[1].name
        findViewById<LinearLayout>(R.id.layout_2).setOnClickListener {
            navegateToSuperHeroDetail(superHeroes[1].id)
        }

        findViewById<TextView>(R.id.superhero_id_3).text = superHeroes[2].id
        findViewById<TextView>(R.id.superhero_name_3).text = superHeroes[2].name
        findViewById<LinearLayout>(R.id.layout_3).setOnClickListener {
            navegateToSuperHeroDetail(superHeroes[2].id)
        }

    }

    private fun navegateToSuperHeroDetail(superheroId: String){
        startActivity(SuperHeroDetailActivity.getIntent(this, superheroId))
    }
}