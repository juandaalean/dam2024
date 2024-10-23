package edu.iesam.dam2024.features.superhero.presentation

import android.view.View
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import edu.iesam.dam2024.app.extensions.loadUrl
import edu.iesam.dam2024.databinding.ViewSuperheroItemBinding
import edu.iesam.dam2024.features.superhero.domain.SuperHero

class SuperHeroViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    private lateinit var binding: ViewSuperheroItemBinding

    fun bind(model: SuperHero){
        binding = ViewSuperheroItemBinding.bind(view)
        binding.apply {
            image.loadUrl(model.urlImage)
            name.text = model.name

        }
    }
}