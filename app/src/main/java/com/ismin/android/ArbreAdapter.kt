package com.ismin.android

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ArbreAdapter(private var arbres: List<Arbre>, private val onArbreClickListener: (Arbre) -> Unit) : RecyclerView.Adapter<ArbreViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArbreViewHolder {
        val rowView = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_arbre, parent, false)
        return ArbreViewHolder(rowView)
    }

    override fun onBindViewHolder(holder: ArbreViewHolder, position: Int) {
        val arbre = arbres[position]

        holder.txvName.text = "Nom: ${arbre.name}"
        holder.txvCommune.text = "Commune: ${arbre.commune}"
        holder.txvDate.text = "Date: ${arbre.date}"
        holder.txvCirconference.text = "Circonférence: ${arbre.circonference}"
        holder.txvHauteur.text = "Hauteur: ${arbre.hauteur}"
        holder.txvFavori.text = if (arbre.est_favori == "true") "Favori: Oui" else "Favori: Non"

        if (arbre.photoUrl.isNullOrEmpty()) {

            holder.txvPhotoUrl.setImageResource(R.drawable.tree)
        } else {

            Glide.with(holder.itemView.context)
                .load(arbre.photoUrl)
                .into(holder.txvPhotoUrl)
        }


        holder.itemView.setOnClickListener {
            onArbreClickListener(arbre)
        }
    }

    override fun getItemCount(): Int {
        return arbres.size
    }

    fun updateArbres(allArbres: List<Arbre>) {
        arbres = allArbres
        notifyDataSetChanged()
    }
}
