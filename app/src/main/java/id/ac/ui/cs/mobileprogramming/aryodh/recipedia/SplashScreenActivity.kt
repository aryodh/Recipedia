package id.ac.ui.cs.mobileprogramming.aryodh.recipedia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import id.ac.ui.cs.mobileprogramming.aryodh.recipedia.ui.main.MainFragment

class SplashScreenActivity : AppCompatActivity() {

    private val SPLASH_TIME_OUT:Long = 2000 // 2 sec
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splashscreen)
        Handler().postDelayed({
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }, SPLASH_TIME_OUT)
    }
}