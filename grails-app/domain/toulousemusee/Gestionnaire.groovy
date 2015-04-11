package toulousemusee

class Gestionnaire {

    String name

    static hasMany = [musees: Musee]

    static constraints = {
        name unique: true
    }
}
