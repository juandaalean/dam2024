package edu.iesam.dam2024.features.superhero.data.local

import android.content.Context
import com.google.gson.Gson
import edu.iesam.dam2024.R
import edu.iesam.dam2024.features.superhero.domain.SuperHero

class SuperHeroXmlLocalDataSource(private val context: Context) {

    private val sharedPref = context.getSharedPreferences(
        context.getString(R.string.name_file_xml), Context.MODE_PRIVATE
    )

    private val gson = Gson()

    fun save(superhero: SuperHero){
        val editor = sharedPref.edit()
        editor.putString(superhero.id, gson.toJson(superhero))
        editor.apply()
    }

    fun saveAll(superhero: List<SuperHero>){
        val editor = sharedPref.edit()
        superhero.forEach { superhero ->
            editor.putString(superhero.id, gson.toJson(superhero))
        }
        editor.apply()
    }


    fun findAll(): List<SuperHero>{
        val superheroes = ArrayList<SuperHero>()
        val mapSuperHeroes = sharedPref.all
        mapSuperHeroes.values.forEach{  jsonSuperhero ->
            val superhero = gson.fromJson(jsonSuperhero as String, SuperHero::class.java)
            superheroes.add(superhero)
        }
        return superheroes
    }

    fun findById(superheroId: String): SuperHero?{
        return sharedPref.getString(superheroId, null)?.let{ superhero ->
            gson.fromJson(superhero, SuperHero::class.java)
        }
    }

    fun delete(){
        sharedPref.edit().clear().apply()
    }

    fun deleteById(superheroId: String){
        sharedPref.edit().remove(superheroId).apply()
    }
}