package id.ac.ui.cs.mobileprogramming.aryodh.recipedia.database

import id.ac.ui.cs.mobileprogramming.aryodh.recipedia.room.Recipe
import id.ac.ui.cs.mobileprogramming.aryodh.recipedia.room.Result
import id.ac.ui.cs.mobileprogramming.aryodh.recipedia.room.Note

class DataGenerator {

    companion object {
        fun getRecipe(): List<Recipe>{
            return listOf(
                Recipe(
                    "McSpicy",
                    135,
                    "Siapkan Fillet Ayam; Buang Air Susu; Campurkan Bumbu; Kocok Telur; Gulingkan Ayam; Goreng; Campurkan Bumbu; Sajikan",
                    "Lebarkan fillet dada ayam/ paha ayam dengan pisau lalu pukul2 dengan roller atau ulekan. Rendam dalam air susu minimal 1 jam agar lebih lembut dan juicy. Simpan di dalam kulkas (bisa di skip proses ini); " +
                            "Buang air susu nya.dan ayam tidak usah dibilas. Beri 1 buah bawang putih yg telah di cincang, 1/2 sdt garam dan merica secukupnya. Ratakan bumbu nya. Lalu diamkan di dalam kulkas setengah jam atau lebih.; " +
                            "Di wadah berbeda. Campurkan 4 sdm tepung terigu dan 1 sdm tepung jagung. Beri 1/4sdt merica secukupnya, 1/4 sdt cabe bubuk dan 1/4 sdt garam. Aduk rata.;" +
                            "Kocok 1 butir telur, 1 1/2 sdm saus cabai, 1/4 sdt merica dan 1 sdm air;" +
                            "Gulingkan dada ayam di campuran terigu smpai merata. Lalu celupkan kedalam telur yg telah di kocok. Terakhir gulingkan kembali ke dalam adonan terigu.;" +
                            "Goreng dengan minyak sedang sampai matang;" +
                            "Campurkan 4 sdm mayonaise, 1/2 sdt merica hitam, 1/4 sdt garam dan 1/4 sdt gula dalam mangkuk. Kemudian oleskan ke dua sisi roti.;" +
                            "Beri ayam dan selada bokor kedalam roti. Sajikan.",
                    "6000;6000;300;60;120;300;60;60"
                ),
                Recipe(
                    "Rendang",
                    3600,
                    "Step Pertama; Step Kedua; Step Ketiga",
                    "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum; " +
                            "has been the industry's standard dummy text ever since the 1500s; " +
                            "when an unknown printer took a galley of type and scrambled it to make a type specimen book. ",
                    "5;300;180"
                ),
                Recipe(
                    "Opor Ayam",
                    1800,
                    "Step Pertama; Step Kedua; Step Ketiga",
                    "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum; " +
                            "has been the industry's standard dummy text ever since the 1500s; " +
                            "when an unknown printer took a galley of type and scrambled it to make a type specimen book. ",
                    "5;300;180"
                )
            )
        }

        fun getResults(): List<Result>{
            return listOf(
                Result(
                    "null",
                    "Good recipe! Nice one!",
                    "12/11/2020",
                    1
                ),
            )
        }

        fun getNotes(): List<Note>{
            return listOf(
                Note(
                    "This is dummy note!",
                    "Delete this note by click the 'x' button"
                )
            )
        }
    }

}