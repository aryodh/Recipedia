package id.ac.ui.cs.mobileprogramming.aryodh.recipedia.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import id.ac.ui.cs.mobileprogramming.aryodh.recipedia.R

class RecipeDetailAdapter(
    private val values: List<String>
) : RecyclerView.Adapter<RecipeDetailAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recipe_detail_item, parent,    false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recipeDetailValue = values[position]
        holder.recipeDetailNumber.text = (position+1).toString()
        holder.recipeDetailContent.text = recipeDetailValue
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val recipeDetailNumber: TextView = view.findViewById(R.id.recipe_detail_number)
        val recipeDetailContent: TextView = view.findViewById(R.id.recipe_detail_content)
    }
}