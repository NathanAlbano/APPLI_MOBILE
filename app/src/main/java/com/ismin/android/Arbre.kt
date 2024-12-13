package com.ismin.android

import java.io.Serializable

data class Arbre(
    val name: String,
    val commune: String,
    val date: String,
    val latitude: String,
    val longitude: String,
    val envergure: String,
    val circonference: String,
    val hauteur: String,
    val id: String ,
    val est_favori: String,
    val photoUrl: String
) : Serializable
