package toulousemusee

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(DemandeVisiteMusee)
class DemandeVisiteMuseeSpec extends Specification {

    @Unroll
    void "test une demande de visite musée valide"(Musee musee, DemandeVisite ddeVisite, Date date) {

        given: "une demande"
        DemandeVisiteMusee dvm = new DemandeVisiteMusee(musee: musee, demandeVisite: ddeVisite, dateDemande: date)

        expect: ""
        dvm.validate() == true

        where:
        musee       | ddeVisite           | date
        Mock(Musee) | Mock(DemandeVisite) | new Date()
    }

    @Unroll
    void "test une demande de visite musée invalide"(Musee musee, DemandeVisite ddeVisite, Date date) {
        given: "une demande"
        DemandeVisiteMusee dvm = new DemandeVisiteMusee(musee: musee, demandeVisite: ddeVisite, dateDemande: date)

        expect: ""
        dvm.validate() == false

        where:
        musee       | ddeVisite           | date
        Mock(Musee) | Mock(DemandeVisite) | new Date() - 1
        Mock(Musee) | null                | new Date()
        null        | Mock(DemandeVisite) | new Date()
    }
	
	void "test to string"(){
		given:"Une demande de visite musée"
			DemandeVisite uneDemandeDeVisite = new DemandeVisite(code:"CODE-1",
				dateDebutPeriode: new Date(),
				dateFinPeriode: new Date() + 1,
				nbPersonne: 4,
				statut: "En cours de traitement")
			DemandeVisiteMusee dvm = new DemandeVisiteMusee(musee:Mock(Musee),
				demandeVisite:uneDemandeDeVisite,
				dateDeamnde:new Date())
		expect:"toString retourne le code de la demande"
			dvm.toString() == "Code : ${uneDemandeDeVisite.code}"
	}
}
