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
import kotlinx.android.synthetic.main.recipe_finish.*
import org.w3c.dom.Text

class RecipeFinishActivity : AppCompatActivity() {
    private var recipeId: Int = 0
    private lateinit var name: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recipe_finish)

        val extras = intent.extras
        if (extras != null) {
            recipeId = extras.getString("recipeId")?.toInt()!!
            name = extras.getString("name").toString()
        }

        recipe_finish_title.text = name

        review_content.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) finish_image_view.setAlpha(50)
            else finish_image_view.setAlpha(255)
        }

        photo_button.setOnClickListener {
            review_content.clearFocus()
        }
    }
}