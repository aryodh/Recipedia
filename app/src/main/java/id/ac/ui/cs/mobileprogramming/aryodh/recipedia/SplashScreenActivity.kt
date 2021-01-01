package id.ac.ui.cs.mobileprogramming.aryodh.recipedia

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.opengl.GLSurfaceView
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import id.ac.ui.cs.mobileprogramming.aryodh.recipedia.ui.main.MainFragment

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var sharedPref: SharedPreferences
    private val SPLASH_TIME_OUT:Long = 2000 // 2 sec
    private lateinit var gLView: GLSurfaceView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPref = this.getSharedPreferences("myPrefs", Context.MODE_PRIVATE)

        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putBoolean("fromBR", false)
        editor.apply()
        gLView = MyGLSurfaceView(this)
        setContentView(gLView)

        Handler().postDelayed({
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }, SPLASH_TIME_OUT)
    }
}