package toulousemusee

import java.util.Date;

import grails.test.mixin.*
import spock.lang.*

class DemandeVisiteServiceIntegrationSpec extends Specification {
	
	DemandeVisiteService demandeVisiteService

	void "Test the save action"() {

		given:"Un demande de visite"
			DemandeVisite demande = new DemandeVisite(
				code:"CODE-0",
				dateDebutPeriode:new Date(),
				dateFinPeriode:new Date() + 1,
				nbPersonne: 4,
				statut:"En cours de traitement")

		and:"des ids de musées"
			List<Long> musees = new ArrayList<Long>()
			musees.add(1L)
			musees.add(2L)
		when:"The save action is executed"
			List<DemandeVisiteMusee> demandes = demandeVisiteService.save(demande, musees)
		
		then:"A visit requests are created"
			DemandeVisite.count() == 1
			DemandeVisiteMusee.count() == 2
			demandes.size() == 2
	}
	
	void "Test the create"(){
		given:
			Date debut = new Date()
			Date fin  = new Date() + 1
			int nbPersonne = 4
		when:
			DemandeVisite demande = demandeVisiteService.createDemandeVisite(debut, fin, nbPersonne)
		then:
			demande
			demande.code == "CODE-1"
			demande.dateDebutPeriode == debut
			demande.dateFinPeriode == fin
			demande.nbPersonne == nbPersonne
			demande.statut == "En cours de traitement"
	}
}
