package toulousemusee

import grails.transaction.Transactional

@Transactional
class MuseeService {

    Musee insertOrUpdate(Musee pMusee, Gestionnaire pGestionnaire) {
        pGestionnaire.addToMusees(pMusee).save()
        pMusee
    }

    void delete(Musee pMusee) {
        pMusee.gestionnaire.removeFromMusees(pMusee)
        pMusee.delete()
    }
}
