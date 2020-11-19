package id.ac.ui.cs.mobileprogramming.aryodh.recipedia.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDAO {

    @Query("SELECT * FROM notes order by id DESC")
    fun getNotes(): LiveData<List<Note>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: Note)

    @Insert
    @JvmSuppressWildcards
    fun insertAll(objects: List<Note>)

    @Delete
    suspend fun delete(note: Note)

    @Update
    suspend fun update(note: Note)

    @Query("DELETE FROM notes")
    fun nukeTable()
}