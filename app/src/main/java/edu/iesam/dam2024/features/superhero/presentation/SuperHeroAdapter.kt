package edu.iesam.dam2024.features.superhero.presentation

import androidx.recyclerview.widget.ListAdapter
import edu.iesam.dam2024.features.superhero.domain.SuperHero

class SuperHeroAdapter(): ListAdapter<SuperHero, SuperHeroViewHolder>(SuperHeroDiffUtil()) {
}