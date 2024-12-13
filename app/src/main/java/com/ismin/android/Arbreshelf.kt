package com.ismin.android

class Arbreshelf {
    private val storage = HashMap<String, Arbre>()


    fun addArbre(arbre: Arbre) {
        storage[arbre.id] = arbre // Utilisation de l'ID comme clé
    }


    fun getArbre(id: String): Arbre {
        return storage[id] ?: throw Exception("Service not found")
    }


    fun getAllArbres(): ArrayList<Arbre> {
        return ArrayList(storage.values
            .sortedBy { arbre -> arbre.name })  // Tri par nom
    }


    fun getArbresOf(commune: String): List<Arbre> {
        return storage.filterValues { arbre -> arbre.commune.equals(commune, ignoreCase = true) }
            .values
            .sortedBy { arbre -> arbre.name }  // Tri par nom
    }


    fun getTotalNumberOfArbres(): Int {
        return storage.size
    }

    fun clear() {
        storage.clear()
    }
}
