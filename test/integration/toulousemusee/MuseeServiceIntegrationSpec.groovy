package toulousemusee


import spock.lang.*

/**
 *
 */
class MuseeServiceIntegrationSpec extends Specification {

    MuseeService museeService

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

        and: "l'adresse existe effectivement en base"
        Adresse.findById(resMusee.adresseId)
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
        def adresseId = resMusee.adresseId

        when: "on tente de le supprimer"
        museeService.delete(unMusee)

        then: "le musée n'est plus en base"
        Musee.findById(resMusee.id) == null

        and: "le gestionnaire n'a plus le musée dans sa liste"
        !unGestionnaire.musees.contains(unMusee)

        and: "l'adresse n'est plus en base"
        Adresse.findById(adresseId) == null
    }
}
