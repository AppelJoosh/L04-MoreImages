package au.edu.swin.sdmd.l04_moreimages

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModel

class MainActivityWithViewModel : AppCompatActivity() {

    // This needs to be a class variable
    private val viewModel: ImageViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Don't forget to update the UI with the current image!
        updateImage()

        val station = findViewById<Button>(R.id.station)
        station.setOnClickListener {
            // Update click listeners to use view model data
            viewModel.image = "station"
            updateImage()
        }

        val college = findViewById<Button>(R.id.college)
        college.setOnClickListener {
            viewModel.image = "college"
            updateImage()
        }

        val onClickTheatre = View.OnClickListener {
            viewModel.image = "theatre"
            updateImage()
        }

        val theatre = findViewById<Button>(R.id.theatre)
        theatre.setOnClickListener(onClickTheatre)

    }

    private fun updateImage() {
        val image = findViewById<ImageView>(R.id.imageView)
        image.setImageDrawable(
            AppCompatResources.getDrawable(
                applicationContext,
                viewModel.getCurrentImage
            )
        )
        image.contentDescription = viewModel.image
    }
}

/**
 * The view model itself: now contains the images
 * Note image should be private and have a getter/setter
 */
class ImageViewModel : ViewModel() {
    var image: String = "station"

    val getCurrentImage: Int
        get() = when (image) {
            "station" -> R.drawable.station
            "college" -> R.drawable.college
            "theatre" -> R.drawable.theatre
            else -> R.drawable.station
        }

}