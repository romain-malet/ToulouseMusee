package toulousemusee

import java.sql.Timestamp

class DemandeVisite {

	String code
	Date dateDebutPeriode
	Date dateFinPeriode
	int nbPersonne
	String statut
    Timestamp version

	static hasMany = [
		demandesVisitesMusees: DemandeVisiteMusee
	]

	static constraints = {
		code unique: true, matches: "CODE-[0-9]+"
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