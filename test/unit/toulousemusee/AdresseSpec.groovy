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
	void "test adresse valide"(int numero, String nomRue, int codePostale, String ville){
		given: "Une adresse avec un numéro de rue, le nom de la rue, le code postal et la vaille"
		Adresse adresse = new Adresse(numero: numero, rue: nomRue, codePostale: codePostale,
                ville: ville)

		expect:"l'adresse est valide"
		adresse.validate() == true

		where:
        numero | nomRue | codePostale | ville
		12     | "nom"  | 31000       | "ville"
	}

    @Unroll
	void "test adresse invalide"(int numero, String nomRue, int codePostale, String ville){
		given: "Une adresse avec un numéro de rue, le nom de la rue, le code postal et la vaille"
		Adresse adresse = new Adresse(numero: numero, rue: nomRue, codePostale: codePostale,
                ville: ville)

		expect:"l'adresse est valide"
		adresse.validate() == false

		where:
        numero | nomRue | codePostale | ville
		0      | "Nom"  | 31000       | "ville"
		12     | ""     | 31000       | "ville"
        12     | null   | 31000       | "ville"
		12     | "Nom"  | 30999       | "ville"
        12     | "Nom"  | 32000       | "ville"
		12     | "Nom"  | 31000       | ""
		12     | "Nom"  | 31000       | null
	}
}
