package id.ac.ui.cs.mobileprogramming.aryodh.recipedia.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.ac.ui.cs.mobileprogramming.aryodh.recipedia.R
import id.ac.ui.cs.mobileprogramming.aryodh.recipedia.room.Chef
import kotlinx.android.synthetic.main.chef_item.view.*


class ChefAdapter(private val chef: List<Chef>?) :
    RecyclerView.Adapter<ChefAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.chef_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = chef?.size ?: 0

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bindItem(chef?.get(position))
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItem(chef: Chef?) {
            itemView.chef_item_title.text = chef?.title
            itemView.chef_item_content.text = chef?.content
            itemView.item_detail_card.setOnClickListener{
                val callIntent = Intent(Intent.ACTION_DIAL)
                callIntent.data = Uri.parse("tel:" + chef?.content)
                itemView.getContext().startActivity(callIntent)
            }
        }
    }
}
