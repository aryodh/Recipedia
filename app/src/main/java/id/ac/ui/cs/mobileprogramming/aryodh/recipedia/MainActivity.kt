package id.ac.ui.cs.mobileprogramming.aryodh.recipedia

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
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

        recipe_card.setOnClickListener{
            startActivity(Intent(this, RecipeBookActivity::class.java).putExtra("data", "data1"))
            this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        }

        notes_card.setOnClickListener{
            startActivity(Intent(this, NoteActivity::class.java))
            this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        }

        chef_card.setOnClickListener{
            if (isNetworkConnected(this)) {
                startActivity(Intent(this, ChefActivity::class.java))
                this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            } else {
                Toast.makeText(applicationContext,"Not connected to network!", Toast.LENGTH_SHORT).show()
            }
        }

    }

    fun isNetworkConnected(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var activeNetworkInfo: NetworkInfo? = null
        activeNetworkInfo = cm.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting
    }
}