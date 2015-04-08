package toulousemusee

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll;

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Adresse)
class AdresseSpec extends Specification {
	@Unroll
	void testAdresseValide(int rue, String nomRue, int codePostale, String ville){
		given: "Une adresse avec un numéro de rue, le nom de la rue, le code postal et la vaille"
		Adresse adresse = new Adresse(rue:rue, nomRue:nomRue, codePostale:codePostale, ville:ville)

		expect:"l'adresse est valide"
		adresse.validate() == true

		where:
		rue | nomRue | codePostale | ville
		21  | "nom"  | 31000       | "ville"
	}
	@Unroll
	void testAdresseInvalide(int rue, String nomRue, int codePostale, String ville){
		given: "Une adresse avec un numéro de rue, le nom de la rue, le code postal et la vaille"
		Adresse adresse = new Adresse(rue:rue, nomRue:nomRue, codePostale:codePostale, ville:ville)

		expect:"l'adresse est valide"
		adresse.validate() == false

		where:
		rue  | nomRue | codePostale | ville
		-12  | null   | 300       | null
		 12  | ""     | 300       | null
		 12  | "Nom"  | 300       | null
		 12  | "Nom"  | 3100      | null
		 12  | "Nom"  | 3100      | ""
	}
}
