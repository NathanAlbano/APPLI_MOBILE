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

class BookListFragment: Fragment() {

    // Define the interface for communication
    interface OnBookClickListener {
        fun onBookClick(book: Book)
    }

    private var onBookClickListener: OnBookClickListener? = null

    private var books: ArrayList<Book> = arrayListOf()
    private lateinit var bookAdapter: BookAdapter
    private lateinit var recyclerView: RecyclerView

    // Ensure that the activity implements the interface
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnBookClickListener) {
            onBookClickListener = context
        } else {
            throw RuntimeException("$context must implement OnBookClickListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        onBookClickListener = null // Avoid memory leaks
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.book_list_fragment, container, false)
        books = arguments?.getSerializable(MY_KEY) as ArrayList<Book>
        recyclerView = rootView.findViewById(R.id.a_main_rcv_books)

        // Define the click listener that will call the interface method
        val onBookClickListener: (Book) -> Unit = { clickedBook ->
            onBookClickListener?.onBookClick(clickedBook)  // Call the method in the activity
        }

        bookAdapter = BookAdapter(books.toList(), onBookClickListener)
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
}
