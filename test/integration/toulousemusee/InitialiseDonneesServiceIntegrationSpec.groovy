package toulousemusee



import spock.lang.*

/**
 *
 */
class InitialiseDonneesServiceIntegrationSpec extends Specification {

    InitialiseDonneesService initData

    void "test l'initialisation des gestionnaires"() {

        given: "il n'y a aucun gestionnaire"
        Gestionnaire.count() == 0;

        when: "on lance le service d'initialisation"
        initData.initGestionnaires()

        then: "il y a 10 gestionnaires en base"
        Gestionnaire.count() == 4

        when: "déjà lancer l'initialisation"
        Gestionnaire.count() == 4

        and: "relance l'initialisation"
        initData.initGestionnaires()

        then: "toujours 10 gestionnaires"
        Gestionnaire.count() == 4
    }

    void "test l'initialisation des musées"() {

        given: "il n'y a aucun musée"
        Musee.count() == 0;

        when: "on lance le service d'initialisation"
        initData.initMusees()

        then: "il y a N musées en base"
        Musee.count() == 12

        when: "déjà lancer l'initialisation"
        Musee.count() == 12

        and: "relance l'initialisation"
        initData.initMusees()

        then: "toujours N musées"
        Musee.count() == 12
    }
}
