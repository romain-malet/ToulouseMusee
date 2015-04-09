package toulousemusee

class DemandeVisiteMusee {

    Date dateDemande
    Musee musee
    DemandeVisite demandeVisite

    static constraints = {
        dateDemande (validator: {val, obj -> val?.after(new Date() - 1)})
    }
}
