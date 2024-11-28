package com.ismin.android

class Bookshelf {
    private val storage = HashMap<String, Book>()

    // Ajouter un livre à la bibliothèque
    fun addBook(book: Book) {
        storage[book.id] = book // Utilisation de l'ID comme clé
    }

    // Récupérer un livre par son ID
    fun getBook(id: String): Book {
        return storage[id] ?: throw Exception("Service not found")
    }

    // Récupérer tous les livres, triés par nom
    fun getAllBooks(): ArrayList<Book> {
        return ArrayList(storage.values
            .sortedBy { book -> book.name })  // Tri par nom
    }

    // Récupérer les livres d'une commune spécifique
    fun getBooksOf(commune: String): List<Book> {
        return storage.filterValues { book -> book.commune.equals(commune, ignoreCase = true) }
            .values
            .sortedBy { book -> book.name }  // Tri par nom
    }

    // Récupérer le nombre total de livres
    fun getTotalNumberOfBooks(): Int {
        return storage.size
    }

    // Effacer tous les livres de la bibliothèque
    fun clear() {
        storage.clear()
    }
}
