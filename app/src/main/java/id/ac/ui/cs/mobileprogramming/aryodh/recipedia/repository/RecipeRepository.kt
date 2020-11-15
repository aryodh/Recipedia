package id.ac.ui.cs.mobileprogramming.aryodh.recipedia.repository

import android.app.Application
import androidx.lifecycle.LiveData
import id.ac.ui.cs.mobileprogramming.aryodh.recipedia.database.DataGenerator
import id.ac.ui.cs.mobileprogramming.aryodh.recipedia.room.Recipe
import id.ac.ui.cs.mobileprogramming.aryodh.recipedia.room.RecipeDAO
import id.ac.ui.cs.mobileprogramming.aryodh.recipedia.database.RecipeDB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class RecipeRepository(application: Application) {
    private val recipeDAO: RecipeDAO?
    private var recipes: LiveData<List<Recipe>>? = null
    private var recipe: Recipe? = null

    init {
        val db = RecipeDB.getInstance(application.applicationContext)
        recipeDAO = db?.recipeDAO()
        recipes = recipeDAO?.getRecipes()
    }

    fun getRecipes(): LiveData<List<Recipe>>? {
        return recipes
    }

    fun getRecipe(id: Int): Recipe? {
        runBlocking{
            this.launch(Dispatchers.IO) {
                recipe = recipeDAO?.getRecipe(id)
            }
        }
        return recipe
    }

    fun insert(recipe: Recipe) = runBlocking {
        this.launch(Dispatchers.IO) {
            recipeDAO?.insert(recipe)
        }
    }

    fun insertAll(recipes: List<Recipe>) = runBlocking {
        this.launch(Dispatchers.IO) {
            recipeDAO?.insertAll(recipes)
        }
    }

    fun delete(recipe: Recipe) {
        runBlocking {
            this.launch(Dispatchers.IO) {
                recipeDAO?.delete(recipe)
            }
        }
    }

    fun deleteAll() {
        runBlocking {
            this.launch(Dispatchers.IO) {
                recipeDAO?.nukeTable()
            }
        }
    }

    fun update(recipe: Recipe) = runBlocking {
        this.launch(Dispatchers.IO) {
            recipeDAO?.update(recipe)
        }
    }
}