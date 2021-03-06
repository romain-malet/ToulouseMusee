package toulousemusee



import spock.lang.*

/**
 *
 */
class InitialiseDonneesServiceIntegrationSpec extends Specification {

    InitialiseDonneesService initialiseDonneesService

    void "test l'initialisation des données pour les musées et leur gestionnaire"() {

        given: "il n'y a aucun musée ni gestionnaire"
        Musee.count() == 0
        Gestionnaire.count() == 0

        when: "on initialise les données des musées"
        initialiseDonneesService.initData()

        then: "il y a 12 musées et 4 gestionnaires"
        Musee.count() == 12
        Gestionnaire.count() == 4

        when: "l'initialisation est déjà faite"
        Musee.count() == 12
        Gestionnaire.count() == 4

        and: "on relance l'initialisation"
        initialiseDonneesService.initData()

        then: "il y a toujours 12 musées et 4 gestionnaires"
        Musee.count() == 12
        Gestionnaire.count() == 4
    }
}
