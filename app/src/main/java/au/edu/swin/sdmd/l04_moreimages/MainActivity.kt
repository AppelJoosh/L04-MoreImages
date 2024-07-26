package au.edu.swin.sdmd.l04_moreimages

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

const val KEY_IMAGE = "image_key"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val image = findViewById<ImageView>(R.id.imageView)

        /**
         * This is the code to restore the state.
         *
         * The override function onRestoreInstanceState can also be used, however
         * this is called at a different point in the lifecycle.
         */
        savedInstanceState?.let {
            image.contentDescription = it.getString(KEY_IMAGE)

            image.apply {
                when (contentDescription) {
                    "station" -> setImageDrawable(
                        getDrawable(
                            R.drawable.station
                        )
                    )
                    "college" -> setImageDrawable(
                        getDrawable(
                            R.drawable.college
                        )
                    )
                    "theatre" -> setImageDrawable(
                        getDrawable(
                            R.drawable.theatre
                        )
                    )
                }
            }
            Log.i("LIFECYCLE", "onRestoreInstanceState")
        }


        val station = findViewById<Button>(R.id.station)
        station.setOnClickListener {
            image.setImageDrawable(
                getDrawable(R.drawable.station))
            image.contentDescription = "station"
        }

        val onClickTheatre = View.OnClickListener {
            image.setImageDrawable(
                getDrawable(R.drawable.theatre))
            image.contentDescription = "theatre"
        }

        val theatre = findViewById<Button>(R.id.theatre)
        theatre.setOnClickListener(onClickTheatre)
    }

    override fun onStop() {
        super.onStop()
        Log.i("LIFECYCLE", "stopped")
    }


    /**
     * This is needed to save state. The variables to save need to be
     * added to a Bundle with a key, in this case "image".
     */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        val image = findViewById<ImageView>(R.id.imageView)
        outState.putString(KEY_IMAGE, image.contentDescription.toString())

        Log.i("LIFECYCLE", "onSaveInstanceState")
    }

    fun onClickCollege(v: View) {
        val image = findViewById<ImageView>(R.id.imageView)
        image.setImageDrawable(
            getDrawable(R.drawable.college))
        image.contentDescription = "college"
    }
}