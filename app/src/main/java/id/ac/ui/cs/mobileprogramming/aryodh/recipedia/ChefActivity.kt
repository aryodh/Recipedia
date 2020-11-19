package id.ac.ui.cs.mobileprogramming.aryodh.recipedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import id.ac.ui.cs.mobileprogramming.aryodh.recipedia.adapter.ChefAdapter
import id.ac.ui.cs.mobileprogramming.aryodh.recipedia.network.NetworkConfig
import id.ac.ui.cs.mobileprogramming.aryodh.recipedia.room.Chef
import kotlinx.android.synthetic.main.chef.*
import retrofit2.Response
import retrofit2.Call
import retrofit2.Callback

class ChefActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.chef)
        val theActivity = this

        NetworkConfig().getService()
            .getTips()
            .enqueue(object : Callback<List<Chef>> {
                override fun onFailure(call: Call<List<Chef>>, t: Throwable) {
                    Toast.makeText(this@ChefActivity, t.localizedMessage, Toast.LENGTH_SHORT).show()
                }
                override fun onResponse(
                    call: Call<List<Chef>>,
                    response: Response<List<Chef>>
                ) {
                    tips_loading.visibility = View.GONE
                    chef_recycler_view.layoutManager = LinearLayoutManager(theActivity)
                    chef_recycler_view.adapter = ChefAdapter(response.body())
                }
            })
    }

    override fun onBackPressed() {
        this.finish()
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}