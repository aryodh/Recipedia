package id.ac.ui.cs.mobileprogramming.aryodh.recipedia.database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import id.ac.ui.cs.mobileprogramming.aryodh.recipedia.room.*
import java.util.concurrent.Executors

@Database(entities = [Recipe::class, Result::class, Note::class], version = 4, exportSchema = false)
abstract class RecipeDB: RoomDatabase() {

    abstract fun recipeDAO(): RecipeDAO
    abstract fun resultDAO(): ResultDAO
    abstract fun noteDAO(): NoteDAO

    companion object {

        private const val DB_NAME = "RECIPE_DB"
        @Volatile private var instance: RecipeDB? = null

        fun getInstance(context: Context): RecipeDB? {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): RecipeDB {
            Log.d("buildDataBase", "Called")
            return Room.databaseBuilder(context, RecipeDB::class.java, DB_NAME)
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        Log.d("buildDataBase", "OnCreate")
                        super.onCreate(db)
                        //pre-populate data
                        Executors.newSingleThreadExecutor().execute {
                            instance?.let {
                                it.recipeDAO().insertAll(DataGenerator.getRecipe())
                                it.resultDAO().insertAll(DataGenerator.getResults())
                                it.noteDAO().insertAll(DataGenerator.getNotes())
                            }
                        }
                    }
                })
                .build()
        }


    }
}