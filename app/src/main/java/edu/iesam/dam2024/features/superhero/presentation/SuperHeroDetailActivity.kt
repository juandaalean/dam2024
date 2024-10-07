package edu.iesam.dam2024.features.superhero.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import edu.iesam.dam2024.R
import edu.iesam.dam2024.app.extensions.loadUrl
import edu.iesam.dam2024.features.superhero.domain.SuperHero


private lateinit var  superHeroFactory: SuperHeroFactory
private lateinit var viewModel: SuperHeroDetailViewModel

class SuperHeroDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_super_hero_detail)
        superHeroFactory = SuperHeroFactory(this)
        viewModel = superHeroFactory.buildSuperHeroDetailViewModel()
        setupObserver()

        getSuperHeroById()?.let{ superheroId ->
            viewModel.viewCreated(superheroId)
            }
        }


    private fun setupObserver(){
        val superHeroObserver = Observer<SuperHeroDetailViewModel.UiState>{ uiState ->
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

    private fun getSuperHeroById(): String? {
        return intent.getStringExtra(KEY_SUPERHERO_ID)
    }

    private fun bindData(superHero: SuperHero){
        val imageView = findViewById<ImageView>(R.id.superImg1)
        Glide
            .with(this)
            .load(superHero.urlImage)
            .into(imageView)

    }

    companion object {
        val KEY_SUPERHERO_ID = "key_superhero_id"

        fun getIntent(context: Context, superheroId: String): Intent {
            val intent = Intent(context, SuperHeroDetailActivity::class.java)
            intent.putExtra(KEY_SUPERHERO_ID, superheroId)
            return intent
        }
    }
}
