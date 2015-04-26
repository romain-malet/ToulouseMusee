package toulousemusee

import java.sql.Timestamp

class Gestionnaire {

    String name
    Timestamp version

    static hasMany = [musees: Musee]

    static constraints = {
        name unique: true
    }
	
	String toString(){
		return "$name"
	}
}
