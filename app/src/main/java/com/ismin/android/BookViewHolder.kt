package com.ismin.android

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BookViewHolder(rootView: View) : RecyclerView.ViewHolder(rootView) {
    // Références aux TextViews pour afficher les propriétés des livres
    var txvName: TextView = rootView.findViewById(R.id.r_book_txv_name)
    var txvCommune: TextView = rootView.findViewById(R.id.r_book_txv_commune)
    var txvPostalCode: TextView = rootView.findViewById(R.id.r_book_txv_postal_code)
    var txvDayStart: TextView = rootView.findViewById(R.id.r_book_txv_day_start)
    var txvDayEnd: TextView = rootView.findViewById(R.id.r_book_txv_day_end)
    var txvStartTime: TextView = rootView.findViewById(R.id.r_book_txv_start_time)
    var txvEndTime: TextView = rootView.findViewById(R.id.r_book_txv_end_time)
}
