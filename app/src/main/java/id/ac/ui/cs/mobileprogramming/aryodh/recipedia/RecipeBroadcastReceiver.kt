package id.ac.ui.cs.mobileprogramming.aryodh.recipedia

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import id.ac.ui.cs.mobileprogramming.aryodh.recipedia.room.Recipe
import id.ac.ui.cs.mobileprogramming.aryodh.recipedia.viewmodel.RecipeDetailViewModel


private const val TAG = "RECIPE_NOTIFICATION"

class RecipeBroadcastReceiver : BroadcastReceiver() {

    var idCounter: Int = 0
    private lateinit var recipeDetailViewModel: RecipeDetailViewModel
    private lateinit var recipe: Recipe
    private lateinit var sharedPref: SharedPreferences

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onReceive(context: Context, intent: Intent) {
        showNotification(context)
        StringBuilder().apply {
            append("Waktu Habis")
            append("Segera balik steak anda sekarang")
            toString()
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun showNotification(context: Context) {
        val name = "recipe_time"
        val descriptionText = "Channel for timer notification"
        val importance = NotificationManager.IMPORTANCE_HIGH
        val mChannel = NotificationChannel("CHANNEL_ID", name, importance)
        mChannel.description = descriptionText

        // Register the channel with the system; you can't change the importance
        // or other notification behaviors after this
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(mChannel)



//        recipeDetailViewModel = ViewModelProvider(this).get(RecipeDetailViewModel::class.java)
//        recipe = recipeDetailViewModel.getRecipe(sharedPref.getInt("recipe_id", 0))!!
//        val intent = Intent(context, RecipeRunActivity::class.java)
//        intent.putExtra("recipeId",recipe.id.toString())
//        intent.putExtra("name",recipe.name)
//        intent.putExtra("title",recipe.recipe_title_list)
//        intent.putExtra("detail",recipe.recipe_detail_list)
//        intent.putExtra("time",recipe.recipe_time)

        sharedPref = context?.getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
        val recipeId = sharedPref.getInt("recipeId", 0)

        val intent = Intent(context, MainActivity::class.java)
//        intent.putExtra("recipeId", recipeId.toString())

        val contentIntent = PendingIntent.getActivity(
            context, 0,
            intent, PendingIntent.FLAG_UPDATE_CURRENT
        )

        val mBuilder = NotificationCompat.Builder(context, "CHANNEL_ID")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Times Up!")
            .setContentText("Go back to your kitchen and move to the next step")
        mBuilder.setContentIntent(contentIntent)
        mBuilder.setDefaults(RingtoneManager.TYPE_NOTIFICATION)
        mBuilder.setAutoCancel(true)

        idCounter += 1
        with(NotificationManagerCompat.from(context)) {
            notify(idCounter, mBuilder.build())
        }
    }
}