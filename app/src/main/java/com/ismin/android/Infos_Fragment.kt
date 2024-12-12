package com.ismin.android

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class InfoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_infos, container, false)

        // Set the text for the TextView
        val infoTextView: TextView = rootView.findViewById(R.id.info_text_view)
        infoTextView.text = """
            Url utilisée pour les données : https://data.opendatasoft.com/explore/dataset/fr-229200506-arbres-remarquables@hauts-de-seine/api/?disjunctive.commune&disjunctive.domaine&disjunctive.nom_francais&disjunctive.critere_general


            Auteurs: Albano Nathan & Alaa Barnat

            License: MIT

            Note des auteurs:

            Cette application recense la localisation et les propriétés des arbres remarquables situés sur le territoire des Hauts-de-Seine (hors propriétés privées).
            Un menu permet de: 

            1. Accéder à la liste des arbres, vous pouvez cliquer sur une référence pour obtenir des informations détaillées et glisser vers la droite pour revenir à la liste déroulante.
            2. Accéder à une map recensant la localisation des données
            3. Accéder aux informations relatives à ce projet
            
            Lorsque vous regardez les spécifités d'un élément, vous pouvez revenir sur la liste des arbres en balayant l'écran vers la droite.
            
            Pour rafraichir les données, il vous suffit de cliquer sur l'icone "liste" du menu.
            
            Mettre en favori des éléments ne fonctionne pas mais est bien instancié côté serveur.
        """.trimIndent()

        return rootView
    }
}