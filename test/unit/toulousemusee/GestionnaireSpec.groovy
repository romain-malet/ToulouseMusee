package toulousemusee

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Gestionnaire)
class GestionnaireSpec extends Specification {

    @Unroll
    void "test la validité d'un gestionnaire valide"(String nom, def _) {

        given: "Un gestionnaire avec un nom"
        Gestionnaire gestionnaire = new Gestionnaire(name: nom)

        expect: "Gestionnaire valide"
        gestionnaire.validate() == true

        where:
        nom | _
        "roger" | _
        "Roger" | _
        "Anne-Marie" | _
    }

    @Unroll
    void "test l'invalidité d'un gestionnaire non valide"(String nom, def _) {

        given: "Un gestionnaire avec un nom"
        Gestionnaire gestionnaire = new Gestionnaire(name: nom)

        expect: "Gestionnaire invalide"
        gestionnaire.validate() == false

        where:
        nom | _
        "" | _
        null | _
    }
	
	void "test to string"(){
		given:"Un gestionnaire"
			Gestionnaire gestionnaire = new Gestionnaire(name: "nom")
		expect:"toString retourne le code de le nom du gestionnaire"
			gestionnaire.toString() == gestionnaire.name
	}
}
