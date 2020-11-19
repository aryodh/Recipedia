package id.ac.ui.cs.mobileprogramming.aryodh.recipedia

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import id.ac.ui.cs.mobileprogramming.aryodh.recipedia.room.Tips
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {

    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("MainActivity", "onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        sharedPref = this.getSharedPreferences("myPrefs", Context.MODE_PRIVATE)

        if (sharedPref.getBoolean("fromBR", false)) {
            super.onBackPressed()
        }

        recipe_menu.setOnClickListener{
            startActivity(Intent(this, RecipeBookActivity::class.java).putExtra("data", "data1"))
            this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        }

        profile_menu.setOnClickListener{
            startActivity(Intent(this, TipsActivity::class.java))
            this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        }

    }
}