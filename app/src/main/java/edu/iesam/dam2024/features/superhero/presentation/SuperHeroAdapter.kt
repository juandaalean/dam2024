package edu.iesam.dam2024.features.superhero.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import edu.iesam.dam2024.R
import edu.iesam.dam2024.features.superhero.domain.SuperHero

class SuperHeroAdapter(): ListAdapter<SuperHero, SuperHeroViewHolder>(SuperHeroDiffUtil()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return SuperHeroViewHolder(layoutInflater.inflate(R.layout.view_superhero_item, parent, false))
    }

    override fun getItemCount(): Int = currentList.size

    override fun onBindViewHolder(holder: SuperHeroViewHolder, position: Int) {
        val item = currentList[position]
        holder.bind(item)
    }
}