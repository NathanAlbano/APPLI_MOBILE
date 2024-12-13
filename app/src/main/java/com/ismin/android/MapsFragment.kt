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
    private var arbres: List<Arbre> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        arguments?.let {
            arbres = it.getSerializable(MY_KEY) as? List<Arbre> ?: emptyList()
            Log.d("MapsFragment", "Arbres received: ${arbres.size}")
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
        var firstValidArbre: Arbre? = null

        // Parcourir la liste des livres
        for (arbre in arbres) {
            try {
                val latitude = arbre.latitude?.toDoubleOrNull()
                val longitude = arbre.longitude?.toDoubleOrNull()

                // Vérification des coordonnées
                if (latitude != null && longitude != null) {
                    val position = LatLng(latitude, longitude)

                    // Ajouter un marqueur
                    googleMap.addMarker(
                        MarkerOptions()
                            .position(position)
                            .title(arbre.name)
                            .snippet("Author: ${arbre.name}")
                    )

                    if (firstValidArbre == null) {
                        firstValidArbre = arbre
                    }

                    Log.d("MapsFragment", "Marker added: ${arbre.name} at ($latitude, $longitude)")
                } else {
                    Log.e("MapsFragment", "Invalid coordinates for arbre: ${arbre.name}")
                }
            } catch (e: Exception) {
                Log.e("MapsFragment", "Error processing arbre: ${arbre.name}", e)
            }
        }

        // Centrer la carte
        if (firstValidArbre != null) {
            val firstPosition = LatLng(
                firstValidArbre.latitude!!.toDouble(),
                firstValidArbre.longitude!!.toDouble()
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
