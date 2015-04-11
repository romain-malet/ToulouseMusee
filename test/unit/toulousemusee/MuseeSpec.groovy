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
	void "test musée valide"(String nom, String horairesOuverture, String telephone,
                             String accesBus, String accesMetro) {
		given: "Un musée valide avec nom, horaires d'ouverture, téléphone, accès bus et metro"
		Musee musee = new Musee(nom: nom, horairesOuverture: horairesOuverture, telephone: telephone,
                accesBus: accesBus, accesMetro: accesMetro,
                adresse: Mock(Adresse), gestionnaire: Mock(Gestionnaire))

		expect: "le musée est valide"
		musee.validate() == true

		where:
		nom   | horairesOuverture | telephone        | accesBus | accesMetro
		"nom" | "9h 18h"          | "0514782314"     | "Bus 63" | "A"
		"nom" | "9h 18h"          | "05 14 78 23 14" | "Bus 63" | "A"
	}

	@Unroll
	void "test musée invalide"(String nom, String horairesOuverture, String telephone,
							   String accesBus, String accesMetro, Adresse adresse,
                               Gestionnaire gestionnaire){
		given: "Un musée est valide avec un nom, les horaires d'ouverture, un numéro de téléphone et les accès bus et metro"
		Musee musee = new Musee(nom: nom, horairesOUvertures: horairesOuverture,
			telephone: telephone, accesBus: accesBus, accesMetro: accesMetro,
			adresse: adresse, gestionnaire: gestionnaire)

		expect:"le musée est valide"
		musee.validate() == false

		where:
		nom    | horairesOuverture | telephone        | accesBus | accesMetro | adresse       | gestionnaire
		null   | "9h 18h"          | "05 14 78 23 14" | "Bus 63" | "A"        | Mock(Adresse) | Mock(Gestionnaire)
		""     | "9h 18h"          | "05 14 78 23 14" | "Bus 63" | "A"        | Mock(Adresse) | Mock(Gestionnaire)
		"nom"  | null              | "05 14 78 23 14" | "Bus 63" | "A"        | Mock(Adresse) | Mock(Gestionnaire)
		"nom"  | ""                | "05 14 78 23 14" | "Bus 63" | "A"        | Mock(Adresse) | Mock(Gestionnaire)
		"nom"  | "9h 18h"          | ""               | "Bus 63" | "A"        | Mock(Adresse) | Mock(Gestionnaire)
		"nom"  | "9h 18h"          | null             | "Bus 63" | "A"        | Mock(Adresse) | Mock(Gestionnaire)
		"nom"  | "9h 18h"          | "un numéro"      | "Bus 63" | "A"        | Mock(Adresse) | Mock(Gestionnaire)
		"nom"  | "9h 18h"          | "05 47 82 3"     | "Bus 63" | "A"        | Mock(Adresse) | Mock(Gestionnaire)
		"nom"  | "9h 18h"          | "05 47 82 32 15" | null     | "A"        | Mock(Adresse) | Mock(Gestionnaire)
		"nom"  | "9h 18h"          | "05 47 82 32 15" | ""       | "A"        | Mock(Adresse) | Mock(Gestionnaire)
		"nom"  | "9h 18h"          | "05 47 82 32 15" | "Bus 63" | null       | Mock(Adresse) | Mock(Gestionnaire)
		"nom"  | "9h 18h"          | "05 47 82 32 15" | "Bus 63" | ""         | Mock(Adresse) | Mock(Gestionnaire)
		"nom"  | "9h 18h"          | "05 47 82 32 15" | "Bus 63" | "A"        | null          | Mock(Gestionnaire)
        "nom"  | "9h 18h"          | "05 47 82 32 15" | "Bus 63" | "A"        | Mock(Adresse) | null
	}
}
