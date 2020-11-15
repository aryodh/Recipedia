package id.ac.ui.cs.mobileprogramming.aryodh.recipedia.database

import id.ac.ui.cs.mobileprogramming.aryodh.recipedia.room.Recipe
import id.ac.ui.cs.mobileprogramming.aryodh.recipedia.room.Result

class DataGenerator {

    companion object {
        fun getRecipe(): List<Recipe>{
            return listOf(
                Recipe(
                    "McSpicy",
                    600,
                    "Step pertama; Step Kedua; Step Ketiga",
                    "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum; " +
                            "has been the industry's standard dummy text ever since the 1500s; " +
                            "when an unknown printer took a galley of type and scrambled it to make a type specimen book. ",
                    "5;300;180"
                ),
                Recipe(
                    "Rendang",
                    3600,
                    "Step pertama; Step Kedua; Step Ketiga",
                    "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum; " +
                            "has been the industry's standard dummy text ever since the 1500s; " +
                            "when an unknown printer took a galley of type and scrambled it to make a type specimen book. ",
                    "120;300;180"
                ),
                Recipe(
                    "Opor Ayam",
                    1800,
                    "Step pertama; Step Kedua; Step Ketiga",
                    "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum; " +
                            "has been the industry's standard dummy text ever since the 1500s; " +
                            "when an unknown printer took a galley of type and scrambled it to make a type specimen book. ",
                    "120;300;180"
                )
            )
        }

        fun getResults(): List<Result>{
            return listOf(
                Result(
                    "null",
                    "1Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum; " +
                            "has been the industry's standard dummy text ever since the 1500s; " +
                            "when an unknown printer took a galley of type and scrambled it to make a type specimen book. ",
                    "12/11/2020",
                    1
                ),
                Result(
                    "null",
                    "2Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum; " +
                            "has been the industry's standard dummy text ever since the 1500s; " +
                            "when an unknown printer took a galley of type and scrambled it to make a type specimen book. ",
                    "12/11/2020",
                    1
                ),
                Result(
                    "null",
                    "3Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum; " +
                            "has been the industry's standard dummy text ever since the 1500s; " +
                            "when an unknown printer took a galley of type and scrambled it to make a type specimen book. ",
                    "12/11/2020",
                    1
                ),

            )
        }
    }

}