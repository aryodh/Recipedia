package id.ac.ui.cs.mobileprogramming.aryodh.recipedia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.ac.ui.cs.mobileprogramming.aryodh.recipedia.adapter.NoteAdapter
import id.ac.ui.cs.mobileprogramming.aryodh.recipedia.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.note.*
class NoteActivity : AppCompatActivity() {
    private lateinit var noteViewModel: NoteViewModel
    private lateinit var noteAdapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.note)

        recyclerViewNoteGenerator()

        create_notes_button.setOnClickListener{
            val intent = Intent(this, NoteAddActivity::class.java)
            this.startActivityForResult(intent, 1)
        }
    }

    private fun recyclerViewNoteGenerator() {
        note_recycler_view.layoutManager = LinearLayoutManager(this)
        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        noteAdapter = NoteAdapter(this, noteViewModel)
        note_recycler_view.adapter = noteAdapter
        noteViewModel.getNotes()?.observe(this, Observer {
            noteAdapter.setNotes(it)
        })
    }

    override fun onBackPressed() {
        this.finish()
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

}