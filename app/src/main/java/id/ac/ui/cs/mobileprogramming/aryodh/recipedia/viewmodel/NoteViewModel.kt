package id.ac.ui.cs.mobileprogramming.aryodh.recipedia.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.AndroidViewModel
import id.ac.ui.cs.mobileprogramming.aryodh.recipedia.repository.NoteRepository
import id.ac.ui.cs.mobileprogramming.aryodh.recipedia.room.Note

class NoteViewModel(application: Application) : AndroidViewModel(application)  {
    private var noteRepository = NoteRepository(application)
    private val notes: LiveData<List<Note>>? = noteRepository.getNotes()

    fun getNotes(): LiveData<List<Note>>?{
        return notes
    }

    fun insert(note: Note) {
        noteRepository.insert(note)
    }

    fun deleteAll() {
        noteRepository.deleteAll()
    }
}