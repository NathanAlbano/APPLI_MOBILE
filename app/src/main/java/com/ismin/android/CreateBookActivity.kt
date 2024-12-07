package com.ismin.android

import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

const val SHOW_BOOK = "Show_BOOK"

class CreateBookActivity : AppCompatActivity() {
    private lateinit var idInput: TextView
    private lateinit var nameInput: TextView
    private lateinit var communeInput: TextView
    private lateinit var dateInput: TextView
    private lateinit var latitudeInput: TextView
    private lateinit var longitudeInput: TextView
    private lateinit var envergureInput: TextView
    private lateinit var circonferenceInput: TextView
    private lateinit var hauteurInput: TextView
    private lateinit var estFavoriInput: RatingBar
    private lateinit var photoUrlImage: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_book)

        // Initialisation des EditText
        idInput = findViewById(R.id.a_create_book_edt_id)
        nameInput = findViewById(R.id.a_create_book_edt_name)
        communeInput = findViewById(R.id.a_create_book_edt_commune)
        dateInput = findViewById(R.id.a_create_book_edt_date)
        latitudeInput = findViewById(R.id.a_create_book_edt_latitude)
        longitudeInput = findViewById(R.id.a_create_book_edt_longitude)
        envergureInput = findViewById(R.id.a_create_book_edt_envergure)
        circonferenceInput = findViewById(R.id.a_create_book_edt_circonference)
        hauteurInput = findViewById(R.id.a_create_book_edt_hauteur)
        estFavoriInput = findViewById(R.id.a_create_book_edt_est_favori)
        try {
            photoUrlImage = findViewById(R.id.a_create_book_edt_photourl)
            Log.d("DEBUG", "photoUrlImage initialized successfully")
        } catch (e: Exception) {
            Log.e("ERROR", "Failed to initialize photoUrlImage", e)
        }


        // Récupération de l'objet Book passé depuis MainActivity
        val book = intent.getSerializableExtra(SHOW_BOOK) as? Book

        // Affichage des données du Book dans les champs
        book?.let {
            idInput.setText(it.id)
            nameInput.setText(it.name)
            communeInput.setText("Commune : "+ it.commune)
            dateInput.setText("Date : "+it.date)
            latitudeInput.setText("Latitude : "+it.latitude)
            longitudeInput.setText("Longitude : "+ it.longitude)
            envergureInput.setText("Envergure : "+ it.envergure)
            circonferenceInput.setText("Circonference : "+it.circonference)
            hauteurInput.setText("Hauteur : "+it.hauteur)

            if (it.est_favori == "vrai") {
                estFavoriInput.rating = 1.0f  // Set full star
            } else {
                estFavoriInput.rating = 0.0f  // No star
            }

            // Display photoUrl as an image or fallback to tree.png
            val photoUrl = it.photoUrl
            if (!photoUrl.isNullOrEmpty()) {
                Glide.with(this)
                    .load(photoUrl)
                    .into(photoUrlImage)
            } else {
                photoUrlImage.setImageResource(R.drawable.tree)
            }
        }
    }
}
