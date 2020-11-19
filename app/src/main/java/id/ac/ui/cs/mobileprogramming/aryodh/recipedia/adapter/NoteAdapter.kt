package id.ac.ui.cs.mobileprogramming.aryodh.recipedia.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.ac.ui.cs.mobileprogramming.aryodh.recipedia.R
import id.ac.ui.cs.mobileprogramming.aryodh.recipedia.room.Note
import kotlinx.android.synthetic.main.note_item.view.*

class NoteAdapter(private val context: Context?) :
    RecyclerView.Adapter<NoteAdapter.ListViewHolder>() {

    private var notes = listOf<Note>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.note_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = notes.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        if (context != null) {
            holder.bindItem(context, notes[position])
        }
    }

    fun setNotes(notes: List<Note>) {
        this.notes = notes
        this.notifyDataSetChanged()
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItem(context: Context, note: Note) {
            itemView.note_item_title.text = note.title
            itemView.note_item_content.text = note.content
        }
    }
}

