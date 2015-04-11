package toulousemusee

class Musee {
	
	String nom
	String horairesOuverture
	String telephone
	String accesBus
	String accesMetro
	
	Adresse adresse
    Gestionnaire gestionnaire

    static hasMany = [ddeVisitMusee: DemandeVisiteMusee]

    static constraints = {
		nom blank:false
		horairesOuverture blank:false
		telephone blank:false, matches:'0\\d\\s*\\d{2}\\s*\\d{2}\\s*\\d{2}\\s*\\d{2}'
    }
}
