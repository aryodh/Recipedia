package id.ac.ui.cs.mobileprogramming.aryodh.recipedia

import android.content.*
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.app.NotificationCompat
import androidx.lifecycle.ViewModelProvider
import id.ac.ui.cs.mobileprogramming.aryodh.recipedia.room.Recipe
import id.ac.ui.cs.mobileprogramming.aryodh.recipedia.viewmodel.RecipeDetailViewModel
import kotlinx.android.synthetic.main.recipe_detail.recipe_detail_title
import kotlinx.android.synthetic.main.recipe_run.*
import java.lang.Thread.sleep
import java.util.concurrent.Executors

class RecipeRunActivity : AppCompatActivity() {
    private var stepCounter: Int = 0
    private var stepLimit: Int = 0
    private var recipeId: Int = 0
    private lateinit var name: String
    private lateinit var titleList: List<String>
    private lateinit var detailList: List<String>
    private lateinit var timeList: List<String>
    private val timerExecutor = Executors.newFixedThreadPool(2)
    private var isRunning: Boolean = false

    private lateinit var recipeDetailViewModel: RecipeDetailViewModel
    private lateinit var recipe: Recipe

    private lateinit var sharedPref: SharedPreferences

    private val broadcastReceiver: BroadcastReceiver = RecipeBroadcastReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recipe_run)

        val extras = intent.extras
        if (extras != null) {
            recipeId = extras.getString("recipeId")?.toInt()!!
        }

        recipeDetailViewModel = ViewModelProvider(this).get(RecipeDetailViewModel::class.java)
        recipe = recipeDetailViewModel.getRecipe(recipeId)!!

        name = recipe.name
        titleList = recipe.recipe_title_list.split(";")!!
        detailList = recipe.recipe_detail_list.split(";")!!
        timeList = recipe.recipe_time.split(";")!!

        sharedPref = this.getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
        Log.d("test boolean", sharedPref.getBoolean("fromBR", false).toString())

        if (sharedPref.getBoolean("fromBR", false)) super.onBackPressed()

        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putInt("recipeId", recipeId)
        editor.putBoolean("fromBR", true)
        editor.apply()
        Log.d("SharedPref", sharedPref.getInt("recipe_id", 0).toString())

        setUpScreen(stepCounter)

        next_button.setOnClickListener{
            if (stepCounter == stepLimit - 1) {
                val intent = Intent(this, RecipeFinishActivity::class.java)
                intent.putExtra("recipeId",recipeId.toString())
                intent.putExtra("name",name)
                this.startActivityForResult(intent, 1)
            }
            if (stepCounter < stepLimit - 1) {
                stepCounter++
                updateData(stepCounter)
                back_button.visibility = View.VISIBLE
                timer.text = converterTime(timeList[stepCounter])
            }
            if (stepCounter == stepLimit - 1) {
                next_button_text.text = getString(R.string.finish)
            }

        }

        back_button.setOnClickListener {
            if (stepCounter > 0) {
                stepCounter--
                updateData(stepCounter)
                next_button_text.text = getString(R.string.next)
                timer.text = converterTime(timeList[stepCounter])
            }
            if (stepCounter == 0) {
                back_button.visibility = View.GONE
            }
        }

        play_run_button.setOnClickListener {
            if (!isRunning) {
                isRunning = true
                startTimer(timeList[stepCounter].toInt())
                next_button.visibility = View.GONE
                back_button.visibility = View.GONE
            } else {
                isRunning = false
                play_run_text.text = "Reset"
                next_button.visibility = View.VISIBLE
                back_button.visibility = View.VISIBLE
            }

        }

    }

    private fun setUpScreen(stepCounter: Int) {
        stepLimit = titleList.size
        recipe_detail_title.text = name
        recipe_detail_number.text = (stepCounter+1).toString()
        recipe_detail_content.text = titleList[stepCounter]
        recipe_specific_content.text = detailList[stepCounter]
        timer.text = converterTime(timeList[stepCounter])
        back_button.visibility = View.GONE
    }

    private fun startTimer(time: Int) {
        var currentTime: Int = time
        val worker = Runnable {
            isRunning = true
            while (currentTime > 0 && isRunning) {
                timer.text = converterTime(currentTime.toString())
                Log.d("Time", converterTime(currentTime.toString()))
                currentTime -= 1
                sleep(1000)
            }
            if (isRunning) {
                stopwatchfinsih()
            }

        }
        timerExecutor.execute(worker)
        play_run_text.text = "Stop"
    }

    private fun stopwatchfinsih() {
        isRunning = false
        timer.text = "Times Up"
        play_run_text.text = "Reset"

        // Broadcast
        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION).apply {
            addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        }
        registerReceiver(broadcastReceiver, filter)

        this@RecipeRunActivity.runOnUiThread(java.lang.Runnable {
            next_button.visibility = View.VISIBLE
            if (stepCounter != 0) {
                back_button.visibility = View.VISIBLE
            }
        })
    }

    private fun updateData(stepCounter: Int) {
        recipe_detail_number.text = (stepCounter+1).toString()
        recipe_detail_content.text = titleList[stepCounter]
        recipe_specific_content.text = detailList[stepCounter]
        play_run_text.text = getString(R.string.start)
    }

    private fun converterTime(time: String): String {
        var minString: String = ""
        var secString: String = ""

        var min= time.toInt() / 60
        var sec = time.toInt() % 60

        minString = if (min < 10) {
            "0$min"
        } else {
            "$min"
        }
        secString = if (sec < 10) {
            "0$sec"
        } else {
            "$sec"
        }

        return "$minString:$secString"
    }

    override fun onActivityResult(requestCode:Int, resultCode:Int, data:Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        this.finish()
    }

    override fun onDestroy() {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putBoolean("fromBR", false)
        editor.apply()
        Log.d("onDestroy", "Calleeeeddd!!")
        super.onDestroy()
    }
}