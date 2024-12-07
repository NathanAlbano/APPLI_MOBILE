package com.ismin.android

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsFragment : Fragment(R.layout.fragment_maps), OnMapReadyCallback {

    private lateinit var googleMap: GoogleMap
    private var books: List<Book> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Retrieve the books from the arguments
        arguments?.let {
            books = it.getSerializable(MY_KEY) as? List<Book> ?: emptyList()
            Log.d("MapsFragment", "Books received: ${books.size}")
        }
    }

    override fun onViewCreated(view: android.view.View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set up the map
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
        mapFragment?.getMapAsync(this)
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map
        Log.d("MapsFragment", "Google Map is ready")

        // Variable pour stocker le premier livre valide
        var firstValidBook: Book? = null

        // Parcourir la liste des livres
        for (book in books) {
            try {
                val latitude = book.latitude?.toDoubleOrNull()
                val longitude = book.longitude?.toDoubleOrNull()

                // Vérification des coordonnées
                if (latitude != null && longitude != null) {
                    val position = LatLng(latitude, longitude)

                    // Ajouter un marqueur
                    googleMap.addMarker(
                        MarkerOptions()
                            .position(position)
                            .title(book.name)
                            .snippet("Author: ${book.name}")
                    )

                    if (firstValidBook == null) {
                        firstValidBook = book
                    }

                    Log.d("MapsFragment", "Marker added: ${book.name} at ($latitude, $longitude)")
                } else {
                    Log.e("MapsFragment", "Invalid coordinates for book: ${book.name}")
                }
            } catch (e: Exception) {
                Log.e("MapsFragment", "Error processing book: ${book.name}", e)
            }
        }

        // Centrer la carte
        if (firstValidBook != null) {
            val firstPosition = LatLng(
                firstValidBook.latitude!!.toDouble(),
                firstValidBook.longitude!!.toDouble()
            )
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(firstPosition, 10f))
        } else {
            // Position par défaut si aucun livre valide n'est trouvé
            val defaultPosition = LatLng(48.8566, 2.3522) // Paris
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(defaultPosition, 5f))
            Log.e("MapsFragment", "No valid coordinates; default position used")
        }
    }
}
