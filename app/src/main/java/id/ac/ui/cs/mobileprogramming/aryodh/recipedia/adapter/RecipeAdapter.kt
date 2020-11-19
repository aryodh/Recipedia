package id.ac.ui.cs.mobileprogramming.aryodh.recipedia.adapter

import android.app.Activity
import android.app.PendingIntent.getActivity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.ac.ui.cs.mobileprogramming.aryodh.recipedia.R
import id.ac.ui.cs.mobileprogramming.aryodh.recipedia.RecipeDetailActivity
import id.ac.ui.cs.mobileprogramming.aryodh.recipedia.room.Recipe
import kotlinx.android.synthetic.main.recipe_item.view.*

class RecipeAdapter(private val context: Context?) :
    RecyclerView.Adapter<RecipeAdapter.ListViewHolder>() {

    private var recipes = listOf<Recipe>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        Log.d("RecipeAdapter", "onCreateViewHolder")
        return ListViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.recipe_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = recipes.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        Log.d("RecipeAdapter", "onBindViewHolder")
        if (context != null) {
            holder.bindItem(context, recipes[position])
        }
    }

    fun setRecipe(recipes: List<Recipe>) {
        Log.d("RecipeAdapter", "setRecipe")
        this.recipes = recipes
        this.notifyDataSetChanged()
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItem(context: Context, recipe: Recipe) {
            Log.d("RecipeAdapter", "bindItem")
            itemView.recipe_name.text = recipe.name
            itemView.setOnClickListener {
                val intent = Intent(context, RecipeDetailActivity::class.java)
                intent.putExtra("id", recipe.id.toString())
                context.startActivity(intent)
                val activity = context as Activity
                activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        }
    }
}

