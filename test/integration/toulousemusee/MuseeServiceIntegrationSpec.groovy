package toulousemusee


import spock.lang.*

/**
 *
 */
class MuseeServiceIntegrationSpec extends Specification {

    MuseeService museeService
    InitialiseDonneesService initialiseDonneesService

    void "test l'insertion d'un musée avec un gestionnaire et une adresse"() {
        given: "une adresse"
        Adresse uneAdresse = new Adresse(
                numero: 42,
                rue: "Boulevard du péril jeune",
                codePostale: 31000,
                ville: "Toulouse"
        )

        and: "un gestionnaire"
        Gestionnaire unGestionnaire = new Gestionnaire(name: "le gestionnaire")

        and: "un musée"
        Musee unMusee = new Musee(
                nom: "le musée",
                horairesOuverture: "de à",
                telephone: "0542369656",
                accesMetro: "Ligne B",
                accesBus: "bus 45",
                adresse: uneAdresse
        )

        when: "on insert le musée en base"
        Musee resMusee = museeService.insertOrUpdate(unMusee, unGestionnaire)

        then: "le musée resultant pointe sur le musée initial"
        resMusee == unMusee

        and: "il n'a pas d'erreur"
        !resMusee.hasErrors()

        and: "il a un id"
        resMusee.id

        and: "il est effectivement en base"
        Musee.findById(resMusee.id) != null

        and: "il a pour gestionnaire le gestionnaire initial"
        resMusee.gestionnaire == unGestionnaire

        and: "le gestionnaire est en base"
        Gestionnaire.findById(resMusee.gestionnaireId) != null

        and: "le gestionnaire a le musée dans sa liste de musées"
        unGestionnaire.musees.contains(resMusee)

        and: "il a pour adresse l'adresse initiale"
        resMusee.adresse == uneAdresse
    }


    void "test la recherche de musée"() {
        given: "l'initialisation des données"
        initialiseDonneesService.initData()

        when: "on recherche un musée par son nom"
        List<Musee> resultats = museeService.search("ARTS", 0, null, 0, 10)

        then: "on obtient 2 musées"
        resultats.size() == 2

        when: "on recherche un musée par code postal"
        resultats = museeService.search(null, 31500, null, 0, 10)

        then: "on obtient 1 musée"
        resultats.size() == 1

        when: "on recherche un musée par nom de rue"
        resultats = museeService.search(null, 0, "METZ", 0, 10)

        then: "on obtient 1 musée"
        resultats.size() == 1

        when: "on recherche un musée par nom de rue"
        resultats = museeService.search(null, 31500, "DE", 0, 10)

        then: "on obtient 1 musée"
        resultats.size() == 1

        when: "on recherche sans critère"
        resultats = museeService.search(null, 0, null, 0, 20)

        then: "on obtient 12 musées"
        resultats.size() == 12
    }


    void "test la recupération des codes postaux"() {
        given: "l'initialisation des données"
        initialiseDonneesService.initData()

        when: "demande la liste des codes postaux"
        List<Integer> postaux = museeService.postalCode()

        then: "on a bien 5 codes postaux"
        postaux.size() == 5

        and: "ils sont ordonnés correctement"
        postaux.get(0) == 31000
        postaux.get(1) == 31200
        postaux.get(4) == 31500
    }


    void "test la suppression d'un musée"() {
        given: "un musée existant en base"
        Adresse uneAdresse = new Adresse(
                numero: 42,
                rue: "Boulevard du péril jeune",
                codePostale: 31000,
                ville: "Toulouse"
        )
        Musee unMusee = new Musee(
                nom: "le musée",
                horairesOuverture: "de à",
                telephone: "0542369656",
                accesMetro: "Ligne B",
                accesBus: "bus 45",
                adresse: uneAdresse
        )
        Gestionnaire unGestionnaire = new Gestionnaire(name: "le gestionnaire")

        Musee resMusee = museeService.insertOrUpdate(unMusee, unGestionnaire)

        when: "on tente de le supprimer"
        museeService.delete(unMusee)

        then: "le musée n'est plus en base"
        Musee.findById(resMusee.id) == null

        and: "le gestionnaire n'a plus le musée dans sa liste"
        !unGestionnaire.musees.contains(unMusee)
    }
}
