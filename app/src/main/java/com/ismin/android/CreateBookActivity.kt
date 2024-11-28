package com.ismin.android

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

val CREATED_BOOK = "CREATED_BOOK"

class CreateBookActivity : AppCompatActivity() {
    private lateinit var idInput: EditText
    private lateinit var nameInput: EditText
    private lateinit var communeInput: EditText
    private lateinit var postalCodeInput: EditText
    private lateinit var latitudeInput: EditText
    private lateinit var longitudeInput: EditText
    private lateinit var dayStartInput: EditText
    private lateinit var dayEndInput: EditText
    private lateinit var startTimeInput: EditText
    private lateinit var endTimeInput: EditText
    private lateinit var saveBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_book)

        // Initialisation des champs de saisie
        idInput = findViewById(R.id.a_create_book_edt_Id)
        nameInput = findViewById(R.id.a_create_book_edt_name)
        communeInput = findViewById(R.id.a_create_book_edt_commune)
        postalCodeInput = findViewById(R.id.a_create_book_edt_postal_code)
        latitudeInput = findViewById(R.id.a_create_book_edt_latitude) // Assurez-vous que ce champ existe dans le XML
        longitudeInput = findViewById(R.id.a_create_book_edt_longitude) // Assurez-vous que ce champ existe dans le XML
        dayStartInput = findViewById(R.id.a_create_book_edt_day_start)
        dayEndInput = findViewById(R.id.a_create_book_edt_day_end)
        startTimeInput = findViewById(R.id.a_create_book_edt_start_time)
        endTimeInput = findViewById(R.id.a_create_book_edt_end_time)
        saveBtn = findViewById(R.id.a_create_book_btn_save)

        // Action lors de l'appui sur le bouton "Save"
        saveBtn.setOnClickListener {
            saveBook()
        }
    }

    private fun saveBook() {
        // Récupération des valeurs des champs de texte
        val id = idInput.text.toString()
        val name = nameInput.text.toString()
        val commune = communeInput.text.toString()
        val postalCode = postalCodeInput.text.toString()
        val latitude = latitudeInput.text.toString()
        val longitude = longitudeInput.text.toString()
        val dayStart = dayStartInput.text.toString()
        val dayEnd = dayEndInput.text.toString()
        val startTime = startTimeInput.text.toString()
        val endTime = endTimeInput.text.toString()

        // Création d'un nouvel objet Book avec les données saisies
        val book = Book(id, name, commune, postalCode, latitude, longitude, dayStart, dayEnd, startTime, endTime)

        // Envoi de l'objet Book à l'activité appelante
        intent.putExtra(CREATED_BOOK, book)
        setResult(RESULT_OK, intent)
        finish()
    }
}
