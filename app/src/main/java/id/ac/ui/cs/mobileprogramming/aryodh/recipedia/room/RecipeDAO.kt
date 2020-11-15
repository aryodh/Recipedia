package id.ac.ui.cs.mobileprogramming.aryodh.recipedia.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface RecipeDAO {

    @Query("SELECT * FROM recipes")
    fun getRecipes(): LiveData<List<Recipe>>

    @Query("SELECT * FROM recipes WHERE id=:id ")
    fun getRecipe(id: Int): Recipe

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(recipes: Recipe)

    @Insert
    @JvmSuppressWildcards
    fun insertAll(objects: List<Recipe>)

    @Delete
    suspend fun delete(recipes: Recipe)

    @Update
    suspend fun update(recipes: Recipe)

    @Query("DELETE FROM recipes")
    fun nukeTable()
}