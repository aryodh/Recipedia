package id.ac.ui.cs.mobileprogramming.aryodh.recipedia.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import id.ac.ui.cs.mobileprogramming.aryodh.recipedia.repository.RecipeRepository
import id.ac.ui.cs.mobileprogramming.aryodh.recipedia.room.Recipe

class RecipeDetailViewModel(application: Application) : AndroidViewModel(application) {

    private var recipeRepository = RecipeRepository(application)
    private lateinit var recipe: Recipe

    fun getRecipe(id: Int): Recipe? {
        recipe = recipeRepository.getRecipe(id)!!
        return recipe
    }
}