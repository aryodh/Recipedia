package id.ac.ui.cs.mobileprogramming.aryodh.recipedia

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.ac.ui.cs.mobileprogramming.aryodh.recipedia.adapter.RecipeDetailAdapter
import id.ac.ui.cs.mobileprogramming.aryodh.recipedia.adapter.ResultAdapter
import id.ac.ui.cs.mobileprogramming.aryodh.recipedia.room.Recipe
import id.ac.ui.cs.mobileprogramming.aryodh.recipedia.viewmodel.RecipeDetailViewModel
import id.ac.ui.cs.mobileprogramming.aryodh.recipedia.viewmodel.ResultViewModel
import kotlinx.android.synthetic.main.recipe_detail.*


class RecipeDetailActivity : AppCompatActivity() {
    private lateinit var recipeDetailViewModel: RecipeDetailViewModel
    private lateinit var recipeDetailAdapter: RecipeDetailAdapter

    private lateinit var resultViewModel: ResultViewModel
    private lateinit var resultAdapter: ResultAdapter

    private lateinit var recipe: Recipe
    private var recipeId: Int = 0
    private lateinit var recipeDetailArray: List<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("RecipeDetailActivity", "onCreate")
        setContentView(R.layout.recipe_detail)

        val extras = intent.extras
        if (extras != null) {
            recipeId = extras.getString("id")?.toInt() ?: 0
        }

        recipeDetailViewModel = ViewModelProvider(this).get(RecipeDetailViewModel::class.java)
        recipe = recipeDetailViewModel.getRecipe(recipeId)!!

        findViewById<TextView>(R.id.recipe_detail_title).text = recipe.name
        est_time.text = recipe.total_time.toString()
        recyclerViewGenerator(recipe)
        recyclerViewResultGenerator(recipe.id)

        findViewById<CardView>(R.id.play_button).setOnClickListener {
            val intent = Intent(this, RecipeRunActivity::class.java)
            intent.putExtra("recipeId",recipe.id.toString())
            intent.putExtra("name",recipe.name)
            intent.putExtra("title",recipe.recipe_title_list)
            intent.putExtra("detail",recipe.recipe_detail_list)
            intent.putExtra("time",recipe.recipe_time)
            this.startActivityForResult(intent, 1)
        }
//        recipeViewModel.deleteAll()
    }

    private fun recyclerViewGenerator(recipe: Recipe) {
        recipeDetailArray = recipe.recipe_title_list.split(";")
        Log.d("RecipeDetailActivity", recipeDetailArray.toString())
        recipe_detail_recycler_view.layoutManager = LinearLayoutManager(this)
        recipeDetailAdapter = RecipeDetailAdapter(recipeDetailArray)

        recipe_detail_recycler_view.adapter = recipeDetailAdapter
    }

    private fun recyclerViewResultGenerator(recipe_id: Int) {
        previous_result_recycler_view.layoutManager = LinearLayoutManager(this)
        resultAdapter = ResultAdapter(this)
        previous_result_recycler_view.adapter = resultAdapter
        resultViewModel = ViewModelProvider(this).get(ResultViewModel::class.java)
        resultViewModel.getResults(recipe_id)?.observe(this, Observer {
            Log.d("test recipe id", recipe_id.toString())
            Log.d("test result", it.toString())
            resultAdapter.setResults(it)
        })
    }

    override fun onBackPressed() {
        this.finish()
        overridePendingTransition(R.anim.slide_out_right, R.anim.slide_in_left);
    }
}