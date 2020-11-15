package id.ac.ui.cs.mobileprogramming.aryodh.recipedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.ac.ui.cs.mobileprogramming.aryodh.recipedia.adapter.RecipeAdapter
import id.ac.ui.cs.mobileprogramming.aryodh.recipedia.adapter.ResultAdapter
import id.ac.ui.cs.mobileprogramming.aryodh.recipedia.room.Recipe
import id.ac.ui.cs.mobileprogramming.aryodh.recipedia.viewmodel.RecipeDetailViewModel
import id.ac.ui.cs.mobileprogramming.aryodh.recipedia.viewmodel.RecipeViewModel
import id.ac.ui.cs.mobileprogramming.aryodh.recipedia.viewmodel.ResultViewModel
import kotlinx.android.synthetic.main.recipe_book.*
import kotlinx.android.synthetic.main.recipe_book.recipe_recycler_view
import kotlinx.android.synthetic.main.recipe_detail.*
import kotlinx.android.synthetic.main.recipe_detail.recipe_detail_title
import kotlinx.android.synthetic.main.recipe_run.*
import org.w3c.dom.Text
import java.lang.Thread.sleep
import java.util.concurrent.Executors

class RecipeRunActivity : AppCompatActivity() {
    private var stepCounter: Int = 0
    private var stepLimit: Int = 0
    private lateinit var name: String
    private lateinit var titleList: List<String>
    private lateinit var detailList: List<String>
    private lateinit var timeList: List<String>
    private val timerExecutor = Executors.newFixedThreadPool(1)
    private var isRunning: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recipe_run)

        val extras = intent.extras
        if (extras != null) {
            name = extras.getString("name").toString()
            titleList = extras.getString("title")?.split(";")!!
            detailList = extras.getString("detail")?.split(";")!!
            timeList = extras.getString("time")?.split(";")!!
        }

        setUpScreen(stepCounter)

        next_button.setOnClickListener{
            if (stepCounter < stepLimit - 1) {
                stepCounter++
                updateData(stepCounter)
                back_button.visibility = View.VISIBLE
                timer.text = converterTime(timeList[stepCounter])
            }
            if (stepCounter == stepLimit - 1) {
                next_button_text.text = "Finish"
            }

        }

        back_button.setOnClickListener {
            if (stepCounter > 0) {
                stepCounter--
                updateData(stepCounter)
                next_button_text.text = "Next"
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
            while (currentTime > 0 && isRunning) {
                timer.text = converterTime(currentTime.toString())
                Log.d("Time", converterTime(currentTime.toString()))
                currentTime -= 1
                sleep(1000)
            }
            if (isRunning) stopwatchfinsih()

        }
        timerExecutor.execute(worker)
        isRunning = true
        play_run_text.text = "Stop"
    }

    private fun stopwatchfinsih() {
        isRunning = false
        this@RecipeRunActivity.runOnUiThread(java.lang.Runnable {
            timer.text = "Times Up"
            play_run_text.text = "Reset"
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
        play_run_text.text = "Start"
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
}