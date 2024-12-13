package com.ismin.android

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ArbreViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val txvName: TextView = view.findViewById(R.id.row_arbre_name)
    val txvCommune: TextView = view.findViewById(R.id.row_arbre_commune)
    val txvDate: TextView = view.findViewById(R.id.row_arbre_date)
    val txvCirconference: TextView = view.findViewById(R.id.row_arbre_circonference)
    val txvHauteur: TextView = view.findViewById(R.id.row_arbre_hauteur)
    val txvFavori: TextView = view.findViewById(R.id.row_arbre_favori)
    val txvPhotoUrl: ImageView = view.findViewById(R.id.row_arbre_photourl)
}

