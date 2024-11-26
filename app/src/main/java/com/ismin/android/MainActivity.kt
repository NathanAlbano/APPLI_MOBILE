package com.ismin.android

import android.content.Intent
import android.os.Bundle
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


class MainActivity : AppCompatActivity() {

    val SERVER_BASE_URL = "https://app-a0efe83d-da7f-4122-b4db-be35bb83c407.cleverapps.io/"

    val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(SERVER_BASE_URL)
        .build()

    val bookService = retrofit.create(BookService::class.java)

    private fun getAllBooksRetrofit(){
        bookService.getBooks()
            .enqueue(object : Callback<List<Book>> {

                override fun onResponse(
                    call: Call<List<Book>>,
                    response: Response<List<Book>>
                ) {
                    val allBooks: List<Book>? = response.body()
                    if (allBooks != null){
                        bookshelf.clear()
                        for (book in allBooks){
                            bookshelf.addBook(book)

                        }
                        displayBookListFragment()
                    }
                }

                override fun onFailure(call: Call<List<Book>>, t: Throwable) {
                    Log.e("helpme", "A L AIDE")

                }
            })
    }

    public val bookshelf = Bookshelf()
    private lateinit var bookAdapter: BookAdapter //Pas bon
    private fun displayBookListFragment(){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val fragment = BookListFragment.newInstance(bookshelf.getAllBooks())

        fragmentTransaction.replace(R.id.a_main_rootview, fragment)
        fragmentTransaction.commit()

    }
    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                val book = it.data?.getSerializableExtra(CREATED_BOOK) as Book
                bookshelf.addBook(book)
                bookAdapter.updateBooks(bookshelf.getAllBooks())
                val fragmentTransaction = supportFragmentManager.beginTransaction()
                val fragment = BookListFragment.newInstance(bookshelf.getAllBooks())

                fragmentTransaction.replace(R.id.a_main_rootview, fragment)
                fragmentTransaction.commit()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initData()

        getAllBooksRetrofit()


        //val fragmentTransaction = supportFragmentManager.beginTransaction()
        //val fragment = BookListFragment.newInstance(bookshelf.getAllBooks())
        //fragmentTransaction.replace(R.id.a_main_rootview, fragment)
        //fragmentTransaction.commit()

        bookAdapter = BookAdapter(bookshelf.getAllBooks())
        findViewById<FloatingActionButton>(R.id.a_main_btn_create_book).setOnClickListener {
            val intent = Intent(this, CreateBookActivity::class.java)
            startForResult.launch(intent)
        }
    }

    private fun initData() {
        bookshelf.addBook(
            Book(
                "978-2253004226",
                "Le meilleur des mondes",
                "Aldous Huxley",
                "1932-01-01"
            )
        )
        bookshelf.addBook(Book("978-2070413119", "1984", "George Orwell", "1949-06-08"))
        bookshelf.addBook(Book("978-2070368229", "Fahrenheit 451", "Ray Bradbury", "1953-10-01"))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.action_delete -> {
                bookshelf.clear()
                bookAdapter.updateBooks(bookshelf.getAllBooks())
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}