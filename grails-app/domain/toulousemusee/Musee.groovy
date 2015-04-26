package toulousemusee

import java.sql.Timestamp

class Musee {

	String nom
	String horairesOuverture
	String telephone
	String accesBus
	String accesMetro
    Timestamp version

	Adresse adresse
	Gestionnaire gestionnaire

    static embedded = ['adresse']
	static hasMany = [ddeVisitMusee: DemandeVisiteMusee]

	static constraints = {
		nom blank:false
		horairesOuverture blank:false
		telephone blank:false, matches:'0\\d\\s*\\d{2}\\s*\\d{2}\\s*\\d{2}\\s*\\d{2}'
		accesBus nullable: true
		accesMetro nullable: true
	}

    static mapping = {
        gestionnaire fetch: 'join'
    }

	String toString(){
		return "$nom"
	}
}
