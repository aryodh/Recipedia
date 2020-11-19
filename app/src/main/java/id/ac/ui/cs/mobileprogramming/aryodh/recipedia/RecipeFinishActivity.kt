package id.ac.ui.cs.mobileprogramming.aryodh.recipedia

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModelProvider
import id.ac.ui.cs.mobileprogramming.aryodh.recipedia.room.Result
import id.ac.ui.cs.mobileprogramming.aryodh.recipedia.viewmodel.ResultViewModel
import kotlinx.android.synthetic.main.previous_result.view.*
import kotlinx.android.synthetic.main.recipe_finish.*
import java.io.File
import java.io.IOException
import java.io.InputStream
import java.text.SimpleDateFormat
import java.util.*


class RecipeFinishActivity : AppCompatActivity() {
    private var recipeId: Int = 0
    private lateinit var name: String
    private lateinit var resultViewModel: ResultViewModel
    private lateinit var currentPhotoPath: String
    val REQUEST_TAKE_PHOTO = 1
    var photoURI: Uri? = null
    var photoFile: File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recipe_finish)

        resultViewModel = ViewModelProvider(this).get(ResultViewModel::class.java)

        val extras = intent.extras
        if (extras != null) {
            recipeId = extras.getString("recipeId")?.toInt()!!
            name = extras.getString("name").toString()
        }

        recipe_finish_title.text = name

        review_content.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) finish_image_view.setAlpha(50)
            else finish_image_view.setAlpha(255)
        }

        photo_button.setOnClickListener {
            review_content.clearFocus()
            dispatchTakePictureIntent()
        }

        home_button.setOnClickListener {
            this.finish()
        }

        submit_button.setOnClickListener{
            val currentDate: String = SimpleDateFormat("EEE, d MMM yyyy", Locale.getDefault()).format(
                Date()
            )
            val content = review_content.text.toString()
            Log.d("date", currentDate)
            if (content.isNotEmpty()) {
                resultViewModel.insert(
                    Result(
                        recipe_id = recipeId,
                        content = content,
                        date = currentDate,
                        image = photoURI.toString()
                    )
                )

                this.finish()
            }
        }
    }

    @Throws(IOException::class)
    private fun createImageFile(): File {
        // Create an image file name
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "JPEG_${timeStamp}_", /* prefix */
            ".jpg", /* suffix */
            storageDir /* directory */
        ).apply {
            // Save a file: path for use with ACTION_VIEW intents
            currentPhotoPath = absolutePath
        }
    }


    private fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            // Ensure that there's a camera activity to handle the intent
            takePictureIntent.resolveActivity(packageManager)?.also {
                // Create the File where the photo should go
                photoFile = try {
                    createImageFile()
                } catch (ex: IOException) {
                    // Error occurred while creating the File
                    null
                }
                // Continue only if the File was successfully created
                photoFile?.also {
                    photoURI = FileProvider.getUriForFile(
                        this,
                        "id.ac.ui.cs.mobileprogramming.aryodh.recipedia.fileprovider",
                        it
                    )
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO)
                }
            }
        }
    }

    override fun onActivityResult(requestCode:Int, resultCode:Int, data:Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d("photoURI", REQUEST_TAKE_PHOTO.toString())
        image_preview.setImageURI(null)
        image_preview.setImageURI(photoURI)
    }
}