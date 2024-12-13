package com.ismin.android

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ArbreshelfUnitTest {
    private val theLordOfTheRings = Arbre(
        title = "The Lord of the Rings",
        author = "J. R. R. Tolkien",
        date = "1954-02-15"
    )

    private val theHobbit = Arbre(
        title = "The Hobbit",
        author = "J. R. R. Tolkien",
        date = "1937-09-21"
    )
    private val aLaRechercheDuTempsPerdu = Arbre(
        title = "À la recherche du temps perdu",
        author = "Marcel Proust",
        date = "1927"
    );

    private lateinit var arbreshelf: Arbreshelf

    @Before
    fun setup() {
        arbreshelf = Arbreshelf()
    }

    @Test
    fun getBook_returns_stored_arbre() {
        arbreshelf.addArbre(theLordOfTheRings);

        assertEquals(arbreshelf.getArbre("The Lord of the Rings"), theLordOfTheRings)
    }

    @Test
    fun getAllBooks_returns_all_stored_arbres() {
        arbreshelf.addArbre(theLordOfTheRings);
        arbreshelf.addArbre(theHobbit);
        arbreshelf.addArbre(aLaRechercheDuTempsPerdu);

        assertEquals(
            arbreshelf.getAllArbres(),
            arrayListOf(theHobbit, theLordOfTheRings, aLaRechercheDuTempsPerdu)
        )
    }

    @Test
    fun getBooksOf_returns_all_arbres_with_input_author() {
        arbreshelf.addArbre(theLordOfTheRings);
        arbreshelf.addArbre(theHobbit);
        arbreshelf.addArbre(aLaRechercheDuTempsPerdu);

        assertEquals(
            arbreshelf.getArbresOf("J. R. R. Tolkien"),
            arrayListOf(theHobbit, theLordOfTheRings)
        )
    }

    @Test
    fun getTotalNumberOfBooks_returns_number_of_stored_arbres() {
        arbreshelf.addArbre(theLordOfTheRings);
        arbreshelf.addArbre(theHobbit);
        arbreshelf.addArbre(aLaRechercheDuTempsPerdu);

        assertEquals(arbreshelf.getTotalNumberOfArbres(), 3)
    }

    @Test
    fun should_not_duplicate_a_book_already_stored() {
        arbreshelf.addArbre(theLordOfTheRings);
        assertEquals(arbreshelf.getTotalNumberOfArbres(), 1)

        arbreshelf.addArbre(theLordOfTheRings);
        assertEquals(arbreshelf.getTotalNumberOfArbres(), 1)
    }
}