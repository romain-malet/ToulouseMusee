package toulousemusee;

import grails.transaction.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Transactional
public class DemandeVisiteService {

	List save(DemandeVisite demandeVisite, List<Long> musees){
		List demandes = new ArrayList<DemandeVisiteMusee>();
		Date date = new Date()
		Map codes = new HashMap<Musee, String>()
		demandeVisite.save(flush:true)
		for (long museeId : musees){
			Musee musee = Musee.get(museeId)
			DemandeVisiteMusee demandeVisiteMusee = new DemandeVisiteMusee(demandeVisite:demandeVisite,
			musee:musee, dateDemande:date)
			demandeVisiteMusee.save flush:true, failOnErrors:true
			demandes.add(demandeVisiteMusee)
		}
		return demandes
	}

	DemandeVisite getDemandeVisite(String code){
		if(code != null)
			code = code.trim()
		return DemandeVisite.findByCode(code)
	}

	List getDemandeVisiteMusees(DemandeVisite demande){
		return DemandeVisiteMusee.findAllByDemandeVisite(demande)
	}

	DemandeVisite createDemandeVisite(Date debut, Date fin, int nbPersonne){
		String code = getNextCode()
		return new DemandeVisite(
				code:code,
				dateDebutPeriode:debut,
				dateFinPeriode:fin,
				nbPersonne: nbPersonne,
				statut:"En cours de traitement")
	}

	String getNextCode(){
		def code = DemandeVisite.createCriteria().get {
			projections { max "id" }
		}
		if(code) {
			return "CODE-${code + 1}" ;
		} else {
			return "CODE-1"
		}
	}
}
