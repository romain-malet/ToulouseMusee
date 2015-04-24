package toulousemusee

class DemandeVisite {

	String code
	Date dateDebutPeriode
	Date dateFinPeriode
	int nbPersonne
	String statut

	String getNextCode(){
		DemandeVisite demande = DemandeVisite.find("from DemandeVisite order by id desc")
		if(demande){
			return "CODE-${demande.id + 1}" ;
		} else {
			return "CODE-0"
		}
	}

	static hasMany = [
		demandesVisitesMusees: DemandeVisiteMusee
	]

	static constraints = {
		code unique: true, matches: "CODE-[0-9]?+"
		dateDebutPeriode (validator: {val, obj -> val?.after(new Date() - 1)})
		dateFinPeriode (validator: {val, obj -> val?.after(obj.dateDebutPeriode)})
		nbPersonne range: 1..6
		statut inList: [
			"En cours de traitement",
			"Confirmée",
			"Refusée"
		]
	}

	String toString(){
		return code
	}
}