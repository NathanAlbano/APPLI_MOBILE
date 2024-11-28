package com.ismin.android

import java.io.Serializable

data class Book(
    val id: String,             // Identifiant unique
    val name: String,           // Nom du service
    val commune: String,        // Nom de la commune
    val postalCode: String,     // Code postal
    val latitude: String,       // Latitude
    val longitude: String,      // Longitude
    val dayStart: String,       // Jour de début
    val dayEnd: String,         // Jour de fin
    val startTime: String,      // Heure d'ouverture
    val endTime: String         // Heure de fermeture
) : Serializable
