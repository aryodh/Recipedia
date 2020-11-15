package id.ac.ui.cs.mobileprogramming.aryodh.recipedia.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.AndroidViewModel
import id.ac.ui.cs.mobileprogramming.aryodh.recipedia.room.Recipe
import id.ac.ui.cs.mobileprogramming.aryodh.recipedia.repository.RecipeRepository

class RecipeViewModel(application: Application) : AndroidViewModel(application)  {
    private var recipeRepository = RecipeRepository(application)
    private val allRecipes: LiveData<List<Recipe>>? = recipeRepository.getRecipes()

    fun getAllRecipes(): LiveData<List<Recipe>>?{
        return allRecipes
    }

    fun insert(recipe: Recipe) {
        recipeRepository.insert(recipe)
    }

    fun deleteAll() {
        recipeRepository.deleteAll()
    }
}