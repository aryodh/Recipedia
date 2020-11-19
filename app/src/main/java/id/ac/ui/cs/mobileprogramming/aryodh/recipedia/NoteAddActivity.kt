package id.ac.ui.cs.mobileprogramming.aryodh.recipedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import id.ac.ui.cs.mobileprogramming.aryodh.recipedia.room.Note
import id.ac.ui.cs.mobileprogramming.aryodh.recipedia.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.note_add.*

class NoteAddActivity : AppCompatActivity() {
    private lateinit var noteViewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.note_add)

        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        note_submit_button.setOnClickListener{
            val title = note_title_input.text.toString()
            val content = note_content_input.text.toString()

            if (title.isNotEmpty() && content.isNotEmpty()) {
                noteViewModel.insert(
                    Note(
                        title = title,
                        content = content,
                    )
                )

                this.finish()
            }
        }
    }

}