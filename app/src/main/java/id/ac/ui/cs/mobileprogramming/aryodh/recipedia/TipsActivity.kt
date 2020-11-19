package id.ac.ui.cs.mobileprogramming.aryodh.recipedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.ac.ui.cs.mobileprogramming.aryodh.recipedia.adapter.ResultAdapter
import id.ac.ui.cs.mobileprogramming.aryodh.recipedia.adapter.TipsAdapter
import id.ac.ui.cs.mobileprogramming.aryodh.recipedia.network.NetworkConfig
import id.ac.ui.cs.mobileprogramming.aryodh.recipedia.room.Tips
import id.ac.ui.cs.mobileprogramming.aryodh.recipedia.viewmodel.ResultViewModel
import kotlinx.android.synthetic.main.recipe_book.*
import kotlinx.android.synthetic.main.recipe_detail.*
import kotlinx.android.synthetic.main.tips.*
import retrofit2.Response
import retrofit2.Call
import retrofit2.Callback

class TipsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tips)
        val theActivity = this

        NetworkConfig().getService()
            .getTips()
            .enqueue(object : Callback<List<Tips>> {
                override fun onFailure(call: Call<List<Tips>>, t: Throwable) {
                    Toast.makeText(this@TipsActivity, t.localizedMessage, Toast.LENGTH_SHORT).show()
                }
                override fun onResponse(
                    call: Call<List<Tips>>,
                    response: Response<List<Tips>>
                ) {
                    tips_recycler_view.layoutManager = LinearLayoutManager(theActivity)
                    tips_recycler_view.adapter = TipsAdapter(response.body())
                }
            })
    }
}