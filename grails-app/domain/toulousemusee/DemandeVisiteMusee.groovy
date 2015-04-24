package toulousemusee

class DemandeVisiteMusee {

	Date dateDemande
	Musee musee
	DemandeVisite demandeVisite

	static constraints = {
		dateDemande (validator: {val, obj -> val?.after(new Date() - 1)})
		musee nullable:false
		demandeVisite nullable:false
	}

	static mapping = { musee fetch: 'join' }

	String toString(){
		return "Code : ${demandeVisite?.code}"
	}
}
