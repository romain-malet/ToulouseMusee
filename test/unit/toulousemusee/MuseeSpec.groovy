package toulousemusee

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll;

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Musee)
class MuseeSpec extends Specification {

	@Unroll
	void "test musée valide"(String nom, String horairesOuverture, String telephone, String accesBus, String accesMetro){
		given: "Un musée est valide avec un nom, les horaires d'ouverture, un numéro de téléphone et les accès bus et metro"
		Musee musee = new Musee(nom:nom, horairesOUvertures:horairesOuverture, telephone:telephone, accesBus:accesBus, accesMetro:accesMetro)

		expect:"le musée est valide"
		musee.validate() == true

		where:
		nom    | horairesOuverture | telephone    | accesBus | accesMetro
		"nom"  | "9h 18h"          | "0514782314" | "Bus 63" | "A"
	}

	@Unroll
	void "test musée invalide"(String nom, String horairesOuverture, String telephone, String accesBus, String accesMetro){
		given: "Un musée est valide avec un nom, les horaires d'ouverture, un numéro de téléphone et les accès bus et metro"
		Musee musee = new Musee(nom:nom, horairesOUvertures:horairesOuverture,
			telephone:telephone, accesBus:accesBus, accesMetro:accesMetro,
			adresse:Mock(Adresse))

		expect:"le musée est valide"
		musee.validate() == true

		where:
		nom    | horairesOuverture | telephone        | accesBus | accesMetro
		null   | "9h 18h"          | "05 14 78 23 14" | "Bus 63" | "A"
		""     | "9h 18h"          | "05 14 78 23 14" | "Bus 63" | "A"
		"nom"  | null              | "05 14 78 23 14" | "Bus 63" | "A"
		"nom"  | ""                | "05 14 78 23 14" | "Bus 63" | "A"
		"nom"  | "9h 18h"          | ""               | "Bus 63" | "A"
		"nom"  | "9h 18h"          | null             | "Bus 63" | "A"
		"nom"  | "9h 18h"          | "un numéro"      | "Bus 63" | "A"
		"nom"  | "9h 18h"          | "05 47 82 3"     | "Bus 63" | "A"
		"nom"  | "9h 18h"          | "05 47 82 32 15" | null     | "A"
		"nom"  | "9h 18h"          | "05 47 82 32 15" | ""       | "A"
		"nom"  | "9h 18h"          | "05 47 82 32 15" | "Bus 63" | null
		"nom"  | "9h 18h"          | "05 47 82 32 15" | "Bus 63" | ""
	}
}
