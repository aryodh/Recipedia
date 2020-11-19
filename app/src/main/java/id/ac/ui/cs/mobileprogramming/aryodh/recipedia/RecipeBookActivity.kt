package id.ac.ui.cs.mobileprogramming.aryodh.recipedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.ac.ui.cs.mobileprogramming.aryodh.recipedia.adapter.RecipeAdapter
import id.ac.ui.cs.mobileprogramming.aryodh.recipedia.adapter.ResultAdapter
import id.ac.ui.cs.mobileprogramming.aryodh.recipedia.viewmodel.RecipeViewModel
import id.ac.ui.cs.mobileprogramming.aryodh.recipedia.viewmodel.ResultViewModel
import kotlinx.android.synthetic.main.recipe_book.*
import kotlinx.android.synthetic.main.recipe_book.recipe_recycler_view
import kotlinx.android.synthetic.main.recipe_detail.*
import org.w3c.dom.Text

class RecipeBookActivity : AppCompatActivity() {
    private lateinit var recipeViewModel: RecipeViewModel
    private lateinit var recipeAdapter: RecipeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recipe_book)

        recyclerViewRecipeGenerator()
//        recipeViewModel.deleteAll()
    }

    private fun recyclerViewRecipeGenerator() {
        recipe_recycler_view.layoutManager = LinearLayoutManager(this)
        recipeAdapter = RecipeAdapter(this)
        recipe_recycler_view.adapter = recipeAdapter
        recipeViewModel = ViewModelProvider(this).get(RecipeViewModel::class.java)
        recipeViewModel.getAllRecipes()?.observe(this, Observer {
            recipeAdapter.setRecipe(it)
        })
    }

    override fun onBackPressed() {
        this.finish()
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

}