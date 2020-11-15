package id.ac.ui.cs.mobileprogramming.aryodh.recipedia.repository

import android.app.Application
import androidx.lifecycle.LiveData
import id.ac.ui.cs.mobileprogramming.aryodh.recipedia.room.Result
import id.ac.ui.cs.mobileprogramming.aryodh.recipedia.room.ResultDAO
import id.ac.ui.cs.mobileprogramming.aryodh.recipedia.database.RecipeDB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class ResultRepository(application: Application) {
    private val resultDAO: ResultDAO?
    private var results: LiveData<List<Result>>? = null

    init {
        val db = RecipeDB.getInstance(application.applicationContext)
        resultDAO = db?.resultDAO()
    }

    fun getResults(recipe_id: Int): LiveData<List<Result>>? {
        runBlocking{
            this.launch(Dispatchers.IO) {
                results = resultDAO?.getResults(recipe_id)
            }
        }
        return results
    }

    fun insert(result: Result) = runBlocking {
        this.launch(Dispatchers.IO) {
            resultDAO?.insert(result)
        }
    }

    fun insertAll(result: List<Result>) = runBlocking {
        this.launch(Dispatchers.IO) {
            resultDAO?.insertAll(result)
        }
    }

    fun delete(result: Result) {
        runBlocking {
            this.launch(Dispatchers.IO) {
                resultDAO?.delete(result)
            }
        }
    }

    fun deleteAll() {
        runBlocking {
            this.launch(Dispatchers.IO) {
                resultDAO?.nukeTable()
            }
        }
    }

    fun update(result: Result) = runBlocking {
        this.launch(Dispatchers.IO) {
            resultDAO?.update(result)
        }
    }
}