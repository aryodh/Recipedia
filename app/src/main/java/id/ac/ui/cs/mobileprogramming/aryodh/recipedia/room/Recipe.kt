package id.ac.ui.cs.mobileprogramming.aryodh.recipedia.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipes")
data class Recipe (
    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "total_time")
    var total_time: Int,

    @ColumnInfo(name = "recipe_title_list")
    var recipe_title_list: String,

    @ColumnInfo(name = "recipe_detail_list")
    var recipe_detail_list: String,

    @ColumnInfo(name = "recipe_time")
    var recipe_time: String,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0
)

@Entity(tableName = "results")
data class Result (

    @ColumnInfo(name= "image_uri")
    var image: String,

    @ColumnInfo(name = "content")
    var content: String,

    @ColumnInfo(name = "date")
    var date: String,

    @ColumnInfo(name = "recipe_id")
    var recipe_id: Int,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0
)