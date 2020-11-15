package id.ac.ui.cs.mobileprogramming.aryodh.recipedia.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ResultDAO {

    @Query("SELECT * FROM results WHERE recipe_id=:recipe_id")
    fun getResults(recipe_id:Int): LiveData<List<Result>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(result: Result)

    @Insert
    @JvmSuppressWildcards
    fun insertAll(objects: List<Result>)

    @Delete
    suspend fun delete(result: Result)

    @Update
    suspend fun update(result: Result)

    @Query("DELETE FROM results")
    fun nukeTable()
}