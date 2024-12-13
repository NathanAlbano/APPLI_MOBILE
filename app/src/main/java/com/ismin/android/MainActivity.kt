package com.ismin.android

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), ArbreListFragment.OnArbreClickListener {

    val SERVER_BASE_URL = "https://app-6822b6a3-4b2d-464e-9264-380a5f46846f.cleverapps.io/"

    val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(SERVER_BASE_URL)
        .build()

    val arbreService = retrofit.create(ArbreService::class.java)

    private fun getAllArbresRetrofit() {
        arbreService.getArbres()
            .enqueue(object : Callback<List<Arbre>> {
                override fun onResponse(call: Call<List<Arbre>>, response: Response<List<Arbre>>) {
                    val allArbres: List<Arbre>? = response.body()
                    if (allArbres != null) {
                        arbreshelf.clear()
                        for (arbre in allArbres) {
                            arbreshelf.addArbre(arbre)
                        }
                        displayArbreListFragment()
                    }
                }

                override fun onFailure(call: Call<List<Arbre>>, t: Throwable) {
                    Log.e("helpme", "ON NE CHARGE PAS LES LIVRES DU SERV ")
                }
            })
    }

    public val arbreshelf = Arbreshelf()
    private lateinit var arbreAdapter: ArbreAdapter

    private fun displayArbreListFragment() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()


        val bundle = Bundle()
        bundle.putSerializable(MY_KEY, ArrayList(arbreshelf.getAllArbres()))


        val fragment = ArbreListFragment()
        fragment.arguments = bundle


        fragmentTransaction.replace(R.id.a_main_rootview, fragment)
        fragmentTransaction.commit()
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        getAllArbresRetrofit()


    }



    override fun onArbreClick(arbre: Arbre) {

        Log.d("MainActivity", "Arbre clicked: ${arbre.name}")

        Log.d("MainActivity", "Arbre clicked: ${arbre.name}")

        val intent = Intent(this, CreateArbreActivity::class.java)
        intent.putExtra(SHOW_ARBRE, arbre)

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


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {


            R.id.menu_map -> {

                val allArbres = arbreshelf.getAllArbres()

                val bundle = Bundle()
                bundle.putSerializable(MY_KEY, ArrayList(allArbres))

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
                getAllArbresRetrofit()
                true // Indicate that the action was handled
            }
            R.id.menu_info -> {
                // New code for menu_info
                val infoFragment = InfoFragment()
                val fragmentTransaction = supportFragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.a_main_rootview, infoFragment)
                fragmentTransaction.addToBackStack(null)
                fragmentTransaction.commit()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }





}

