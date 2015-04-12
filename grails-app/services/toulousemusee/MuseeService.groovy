package toulousemusee

import grails.transaction.Transactional
import grails.gorm.DetachedCriteria

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

    List<Musee> search(String nomMusee, int postal, String nomRue) {
        def criteria = new DetachedCriteria(Musee).build {
            if (nomMusee) {
                like 'nom', "%${nomMusee}%"
            }
        }
        criteria = criteria.build {
            if (postal) {
                or {
                    eq 'adresse.codePostale', postal
                }
            }
            if (nomRue) {
                or {
                    like 'adresse.rue', "%${nomRue}%"
                }
            }
        }
        def results = criteria.list()
        results
    }

    List<Integer> postalCode() {
        def postals = Musee.withCriteria {
            projections {
                distinct 'adresse.codePostale'
            }
            order 'adresse.codePostale'
        }
        postals
    }
}
