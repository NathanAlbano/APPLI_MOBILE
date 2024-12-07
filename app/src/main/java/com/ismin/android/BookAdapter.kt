package com.ismin.android

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class BookAdapter(private var books: List<Book>, private val onBookClickListener: (Book) -> Unit) : RecyclerView.Adapter<BookViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val rowView = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_book, parent, false)
        return BookViewHolder(rowView)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = books[position]
        // Mise à jour des TextViews en fonction des nouvelles variables
        holder.txvName.text = "Nom: ${book.name}"
        holder.txvCommune.text = "Commune: ${book.commune}"
        holder.txvDate.text = "Date: ${book.date}"
        holder.txvCirconference.text = "Circonférence: ${book.circonference}"
        holder.txvHauteur.text = "Hauteur: ${book.hauteur}"
        holder.txvFavori.text = if (book.est_favori == "true") "Favori: Oui" else "Favori: Non"

        if (book.photoUrl.isNullOrEmpty()) {
            // Load the default image if photoUrl is null or empty
            holder.txvPhotoUrl.setImageResource(R.drawable.tree)
        } else {
            // Load the image from the URL using Glide if photoUrl is not empty
            Glide.with(holder.itemView.context)
                .load(book.photoUrl)  // Replace `book.photoUrl` with the actual URL of the image
                .into(holder.txvPhotoUrl)
        }

        // Set the click listener for the entire item
        holder.itemView.setOnClickListener {
            onBookClickListener(book) // Trigger the click listener and pass the clicked book
        }
    }

    override fun getItemCount(): Int {
        return books.size
    }

    fun updateBooks(allBooks: List<Book>) {
        books = allBooks
        notifyDataSetChanged()
    }
}
