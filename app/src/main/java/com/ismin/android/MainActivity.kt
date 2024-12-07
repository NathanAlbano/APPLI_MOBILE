package com.ismin.android

import android.content.Intent
import android.os.Bundle
import android.os.Parcel
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), BookListFragment.OnBookClickListener {

    val SERVER_BASE_URL = "https://app-6822b6a3-4b2d-464e-9264-380a5f46846f.cleverapps.io/"

    val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(SERVER_BASE_URL)
        .build()

    val bookService = retrofit.create(BookService::class.java)

    private fun getAllBooksRetrofit() {
        bookService.getBooks()
            .enqueue(object : Callback<List<Book>> {
                override fun onResponse(call: Call<List<Book>>, response: Response<List<Book>>) {
                    val allBooks: List<Book>? = response.body()
                    if (allBooks != null) {
                        bookshelf.clear()
                        for (book in allBooks) {
                            bookshelf.addBook(book)
                        }
                        displayBookListFragment()
                    }
                }

                override fun onFailure(call: Call<List<Book>>, t: Throwable) {
                    Log.e("helpme", "ON NE CHARGE PAS LES LIVRES DU SERV ")
                }
            })
    }

    public val bookshelf = Bookshelf()
    private lateinit var bookAdapter: BookAdapter

    private fun displayBookListFragment() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()

        // Create a Bundle and pass the list of books to the fragment
        val bundle = Bundle()
        bundle.putSerializable(MY_KEY, ArrayList(bookshelf.getAllBooks()))  // Pass the books to the fragment

        // Create the fragment and set its arguments
        val fragment = BookListFragment()
        fragment.arguments = bundle

        // Replace the current fragment with the new one
        fragmentTransaction.replace(R.id.a_main_rootview, fragment)
        fragmentTransaction.commit()
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Fetch the books from the server
        getAllBooksRetrofit()


    }


    // Implementing the onBookClick method to handle book clicks
    override fun onBookClick(book: Book) {
        // Log the book clicked
        Log.d("MainActivity", "Book clicked: ${book.name}")

        Log.d("MainActivity", "Book clicked: ${book.name}")

        val intent = Intent(this, CreateBookActivity::class.java)
        intent.putExtra(SHOW_BOOK, book)

        // Log the bundle contents
        val bundle = intent.extras
        Log.d("MainActivity", "Intent extras: $bundle")

        startActivity(intent)


    }






    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu_main, menu)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        //super.onSaveInstanceState(outState)
        //super.onSaveInstanceState(outState)

        // Measure the size of the Bundle
        //val parcel = Parcel.obtain()
        //outState.writeToParcel(parcel, 0)
        //val size = parcel.dataSize()
        //parcel.recycle()

        //Log.d("CreateBookActivity", "Bundle size: $size bytes")
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {


            R.id.menu_map -> {
                // Passer la liste des livres au MapsFragment
                // Envoyer seulement les 10 premiers éléments
                val allBooks = bookshelf.getAllBooks()
                //val firstTenBooks = if (allBooks.size > 10) allBooks.subList(0, 10) else allBooks

                // Créer un Bundle et passer la liste des 10 premiers livres
                val bundle = Bundle()
                bundle.putSerializable(MY_KEY, ArrayList(allBooks)) // Passer uniquement les 10 premiers livres

                //bundle.putSerializable(MY_KEY, ArrayList(firstTenBooks)) // Passer uniquement les 10 premiers livres


                // Créer et afficher le fragment
                val mapsFragment = MapsFragment()
                mapsFragment.arguments = bundle

                val fragmentTransaction = supportFragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.a_main_rootview, mapsFragment)
                fragmentTransaction.addToBackStack(null) // Permet de revenir en arrière
                fragmentTransaction.commit()

                true
            }

            R.id.menu_list -> {
                // Execute the function when menu_list is selected
                getAllBooksRetrofit()
                true // Indicate that the action was handled
            }

            else -> super.onOptionsItemSelected(item)
        }
    }


}

