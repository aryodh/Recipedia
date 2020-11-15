package id.ac.ui.cs.mobileprogramming.aryodh.recipedia.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import id.ac.ui.cs.mobileprogramming.aryodh.recipedia.repository.ResultRepository
import id.ac.ui.cs.mobileprogramming.aryodh.recipedia.room.Result

class ResultViewModel(application: Application) : AndroidViewModel(application) {

    private var resultRepository = ResultRepository(application)
    private lateinit var results: LiveData<List<Result>>

    fun getResults(recipe_id: Int): LiveData<List<Result>> {
        results = resultRepository.getResults(recipe_id)!!
        return results
    }
}