package toulousemusee

class DemandeVisite {

    int code
    Date dateDebutPeriode
    Date dateFinPeriode
    int nbPersonne
    boolean statut
    Musee musee

    static hasMany = [ddeVisitMusee: DemandeVisiteMusee]

    static constraints = {
        code unique: true, min: 1
        dateDebutPeriode (validator: {val, obj -> val?.after(new Date() - 1)})
        dateFinPeriode (validator: {val, obj -> val?.after(obj.dateDebutPeriode)})
        nbPersonne range: 1..6
    }
}
