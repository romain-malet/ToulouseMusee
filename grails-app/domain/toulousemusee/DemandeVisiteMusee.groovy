package toulousemusee

import java.sql.Timestamp

class DemandeVisiteMusee {

	Date dateDemande
	Musee musee
	DemandeVisite demandeVisite
    Timestamp version

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
