package id.ac.ui.cs.mobileprogramming.aryodh.recipedia.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.ac.ui.cs.mobileprogramming.aryodh.recipedia.R
import id.ac.ui.cs.mobileprogramming.aryodh.recipedia.room.Tips
import kotlinx.android.synthetic.main.tips_item.view.*

class TipsAdapter(private val tips: List<Tips>?) :
    RecyclerView.Adapter<TipsAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.tips_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = tips?.size ?: 0

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bindItem(tips?.get(position))
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItem(tips: Tips?) {
            itemView.tips_item_title.text = tips?.title
            itemView.tips_item_content.text = tips?.content
        }
    }
}
