package com.ismin.android

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

private const val MY_KEY = "key"

class BookListFragment: Fragment() {
    private var books: ArrayList<Book> = arrayListOf()
    private lateinit var bookAdapter: BookAdapter
    private lateinit var recyclerView: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.book_list_fragment, container, false)
        books = arguments?.getSerializable(MY_KEY) as ArrayList<Book>
        recyclerView = rootView.findViewById(R.id.a_main_rcv_books)
        bookAdapter = BookAdapter(books.toList())
        recyclerView.adapter = bookAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )

        // Inflate the layout for this fragment
        return rootView
    }

    companion object {

        @JvmStatic
        fun newInstance(books: ArrayList<Book>) =
            BookListFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(MY_KEY, books)
                }
            }
    }
}