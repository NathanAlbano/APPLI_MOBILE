package com.ismin.android

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BookViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val txvName: TextView = view.findViewById(R.id.row_book_name)
    val txvCommune: TextView = view.findViewById(R.id.row_book_commune)
    val txvDate: TextView = view.findViewById(R.id.row_book_date)
    val txvCirconference: TextView = view.findViewById(R.id.row_book_circonference)
    val txvHauteur: TextView = view.findViewById(R.id.row_book_hauteur)
    val txvFavori: TextView = view.findViewById(R.id.row_book_favori)
    val txvPhotoUrl: ImageView = view.findViewById(R.id.row_book_photourl)
}

