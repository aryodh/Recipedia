package id.ac.ui.cs.mobileprogramming.aryodh.recipedia.repository

import android.app.Application
import androidx.lifecycle.LiveData
import id.ac.ui.cs.mobileprogramming.aryodh.recipedia.room.Note
import id.ac.ui.cs.mobileprogramming.aryodh.recipedia.room.ResultDAO
import id.ac.ui.cs.mobileprogramming.aryodh.recipedia.database.RecipeDB
import id.ac.ui.cs.mobileprogramming.aryodh.recipedia.room.NoteDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class NoteRepository(application: Application) {
    private val noteDAO: NoteDAO?
    private var notes: LiveData<List<Note>>? = null

    init {
        val db = RecipeDB.getInstance(application.applicationContext)
        noteDAO = db?.noteDAO()
    }

    fun getNotes(): LiveData<List<Note>>? {
        runBlocking{
            this.launch(Dispatchers.IO) {
                notes = noteDAO?.getNotes()
            }
        }
        return notes
    }

    fun insert(note: Note) = runBlocking {
        this.launch(Dispatchers.IO) {
            noteDAO?.insert(note)
        }
    }

    fun insertAll(note: List<Note>) = runBlocking {
        this.launch(Dispatchers.IO) {
            noteDAO?.insertAll(note)
        }
    }

    fun delete(note: Note) {
        runBlocking {
            this.launch(Dispatchers.IO) {
                noteDAO?.delete(note)
            }
        }
    }

    fun deleteAll() {
        runBlocking {
            this.launch(Dispatchers.IO) {
                noteDAO?.nukeTable()
            }
        }
    }

    fun update(note: Note) = runBlocking {
        this.launch(Dispatchers.IO) {
            noteDAO?.update(note)
        }
    }
}