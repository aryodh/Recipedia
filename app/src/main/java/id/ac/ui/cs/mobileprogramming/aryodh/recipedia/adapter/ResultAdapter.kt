package id.ac.ui.cs.mobileprogramming.aryodh.recipedia.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.ac.ui.cs.mobileprogramming.aryodh.recipedia.R
import id.ac.ui.cs.mobileprogramming.aryodh.recipedia.room.Result
import kotlinx.android.synthetic.main.previous_result.view.*

class ResultAdapter(private val context: Context?) :
    RecyclerView.Adapter<ResultAdapter.ListViewHolder>() {

    private var results = listOf<Result>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        Log.d("ResultAdapter", "onCreateViewHolder")
        return ListViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.previous_result,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = results.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        Log.d("ResultAdapter", "onBindViewHolder")
        if (context != null) {
            holder.bindItem(context, results[position])
        }
    }

    fun setResults(results: List<Result>) {
        Log.d("ResultAdapter", "setResult")
        this.results = results
        this.notifyDataSetChanged()
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItem(context: Context, result: Result) {
            Log.d("ResultAdapter", "bindItem")
            itemView.previous_result_content.text = result.content
            itemView.previous_result_date.text = result.date
            itemView.previous_result_image.setImageResource(R.drawable.rendang)
        }
    }
}

