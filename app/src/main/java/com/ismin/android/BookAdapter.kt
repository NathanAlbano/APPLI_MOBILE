

package com.ismin.android

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class BookAdapter(private var books: List<Book>) : RecyclerView.Adapter<BookViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val rowView = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_book, parent, false)
        return BookViewHolder(rowView)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = books[position]
        //holder.txvId.text = "ID: ${book.id}"                // Affichage de l'ID
        holder.txvName.text = "Nom: ${book.name}"            // Affichage du nom du service
        holder.txvCommune.text = "Commune: ${book.commune}"  // Affichage de la commune
        holder.txvPostalCode.text = "Code postal: ${book.postalCode}"  // Code postal
        holder.txvDayStart.text = "Début: ${book.dayStart}"  // Jour de début
        holder.txvDayEnd.text = "Fin: ${book.dayEnd}"        // Jour de fin
        holder.txvStartTime.text = "Ouverture: ${book.startTime}"  // Heure d'ouverture
        holder.txvEndTime.text = "Fermeture: ${book.endTime}"  // Heure de fermeture
    }

    override fun getItemCount(): Int {
        return books.size
    }

    fun updateBooks(allBooks: List<Book>) {
        books = allBooks
        notifyDataSetChanged()
    }
}
