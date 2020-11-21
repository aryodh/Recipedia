package id.ac.ui.cs.mobileprogramming.aryodh.recipedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.ac.ui.cs.mobileprogramming.aryodh.recipedia.adapter.RecipeAdapter
import id.ac.ui.cs.mobileprogramming.aryodh.recipedia.viewmodel.RecipeViewModel
import kotlinx.android.synthetic.main.recipe_book.recipe_recycler_view

class RecipeBookActivity : AppCompatActivity() {
    private lateinit var recipeViewModel: RecipeViewModel
    private lateinit var recipeAdapter: RecipeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recipe_book)

        recyclerViewRecipeGenerator()
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