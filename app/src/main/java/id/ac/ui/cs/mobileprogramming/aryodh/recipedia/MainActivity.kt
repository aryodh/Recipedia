package id.ac.ui.cs.mobileprogramming.aryodh.recipedia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("MainActivity", "onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val recipe_link: TextView = findViewById(R.id.recipe_menu)

        recipe_link.setOnClickListener{
            startActivity(Intent(this, RecipeBookActivity::class.java).putExtra("data", "data1"))
        }


    }
}