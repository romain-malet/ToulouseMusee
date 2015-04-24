package toulousemusee;

import grails.transaction.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Transactional
public class DemandeVisiteMuseeService {

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
		return DemandeVisite.findByCode(code)
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
		DemandeVisite demande = DemandeVisite.find("from DemandeVisite order by id desc")
		if(demande){
			return "CODE-${demande.id + 1}" ;
		} else {
			return "CODE-1"
		}
	}
}
