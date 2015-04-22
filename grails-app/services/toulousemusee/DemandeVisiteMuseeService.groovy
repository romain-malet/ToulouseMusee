package toulousemusee;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DemandeVisiteMuseeService {

	List save(Date debut, Date fin, int nbPersonne, List<Long> musees){
		List demandes = new ArrayList<DemandeVisiteMusee>();
		Date date = new Date()
		Map codes = new HashMap<Musee, String>()
		for (long museeId : musees){
			Musee musee = Musee.get(museeId)
			DemandeVisite demandeVisite = createDemandeVisite(debut, fin, nbPersonne)
			demandeVisite.save(flush:true)
			codes.put(musee, demandeVisite.code)
			DemandeVisiteMusee demandeVisiteMusee = new DemandeVisiteMusee(demandeVisite:demandeVisite,
			musee:musee, dateDemande:date)
			demandeVisiteMusee.save flush:true
			demandes.add(demandeVisiteMusee)
		}
		return demandes
	}

	DemandeVisite createDemandeVisite(Date debut, Date fin, int nbPersonne){
		String code = getNextCode()
		return new DemandeVisite(
				code:code,
				dateDebutPeriode:params.dateDebutPeriode,
				dateFinPeriode:params.dateFinPeriode,
				nbPersonne: params.nbPersonne as int)
	}

	String getNextCode(){
		DemandeVisite demande = DemandeVisite.find("from DemandeVisite order by id desc")
		if(demande){
			return "CODE-${demande.id + 1}" ;
		} else {
			return "CODE-0"
		}
	}
}
