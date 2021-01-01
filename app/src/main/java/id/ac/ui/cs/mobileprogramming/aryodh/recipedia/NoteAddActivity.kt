package id.ac.ui.cs.mobileprogramming.aryodh.recipedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import androidx.lifecycle.ViewModelProvider
import id.ac.ui.cs.mobileprogramming.aryodh.recipedia.room.Note
import id.ac.ui.cs.mobileprogramming.aryodh.recipedia.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.note_add.*
import android.text.TextWatcher

class NoteAddActivity : AppCompatActivity() {

    private external fun charCount(input_name: String) : String
    private lateinit var noteViewModel: NoteViewModel

    companion object {
        // Used to load the 'jni' library on application startup.
        init {
            System.loadLibrary("charCounter")
        }
    }

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

        note_content_input.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                val result = charCount(note_content_input.text.toString())
                charTotal.text = ("$result Characters")
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }



}