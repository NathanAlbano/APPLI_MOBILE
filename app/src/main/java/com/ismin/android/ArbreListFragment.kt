package com.ismin.android

import android.content.Context  // Add this import
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

const val MY_KEY = "key"

class ArbreListFragment: Fragment() {


    interface OnArbreClickListener {
        fun onArbreClick(arbre: Arbre)
    }

    private var onArbreClickListener: OnArbreClickListener? = null

    private var arbres: ArrayList<Arbre> = arrayListOf()
    private lateinit var arbreAdapter: ArbreAdapter
    private lateinit var recyclerView: RecyclerView


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnArbreClickListener) {
            onArbreClickListener = context
        } else {
            throw RuntimeException("$context must implement OnArbreClickListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        onArbreClickListener = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.arbre_list_fragment, container, false)
        arbres = arguments?.getSerializable(MY_KEY) as ArrayList<Arbre>
        recyclerView = rootView.findViewById(R.id.a_main_rcv_arbres)


        val onArbreClickListener: (Arbre) -> Unit = { clickedArbre ->
            onArbreClickListener?.onArbreClick(clickedArbre)
        }

        arbreAdapter = ArbreAdapter(arbres.toList(), onArbreClickListener)
        recyclerView.adapter = arbreAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )


        return rootView
    }
}
