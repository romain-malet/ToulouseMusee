package toulousemusee

class Musee {
	
	String nom
	String 

    static hasMany = [ddeVisitMusee: DemandeVisiteMusee]

    static constraints = {
    }
}
