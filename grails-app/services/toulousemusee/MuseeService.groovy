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

    List<Musee> search(String nomMusee, int postal, String nomRue, int offset, int max) {
        def criteria = new DetachedCriteria(Musee).build {
            if (nomMusee) {
                like 'nom', "%${nomMusee.toUpperCase()}%"
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
                    like 'adresse.rue', "%${nomRue.toUpperCase()}%"
                }
            }
        }
        def results = criteria.list(max: max, offset: offset)
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
