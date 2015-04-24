package toulousemusee

import grails.test.mixin.*
import spock.lang.*

class DemandeVisiteMuseeServiceIntegrationSpec extends Specification {
	
	DemandeVisiteMuseeService demandeVisiteMuseeService

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
			List<DemandeVisiteMusee> demandes = demandeVisiteMuseeService.save(demande, musees)
		
		then:"A visit requests are created"
			DemandeVisite.count() == 1
			demandes.size() == 2
	}
}
