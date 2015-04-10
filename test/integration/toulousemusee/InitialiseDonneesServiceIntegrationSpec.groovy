package toulousemusee



import spock.lang.*

/**
 *
 */
class InitialiseDonneesServiceIntegrationSpec extends Specification {

    InitialiseDonneesService initialiseDonneesService

    void "test l'initialisation des données pour les musées et les gestionnaires"() {

        given: "il n'y a aucun musée ni gestionnaires"
        Musee.count() == 0;
        Gestionnaire.count() == 0;

        when: "on lance le service d'initialisation des données"
        initialiseDonneesService.initData()

        then: "il y a 12 musées et 4 gestionnaires"
        Musee.count() == 12
        Gestionnaire.count() == 4

        when: "initialisation déjà faite"
        Musee.count() == 12
        Gestionnaire.count() == 4

        and: "relance l'initialisation"
        initialiseDonneesService.initData()

        then: "toujours 12 musées et 4 gestionnaires"
        Musee.count() == 12
        Gestionnaire.count() == 4
    }
}
