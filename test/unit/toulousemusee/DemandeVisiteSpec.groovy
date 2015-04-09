package toulousemusee

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(DemandeVisite)
class DemandeVisiteSpec extends Specification {

    void "test la validité d'une demande valide"(int code, Date debut, Date fin,
                                                 int nbPersonne, Musee unMusee, boolean statut) {

        given: "Une demande de visite avec un code, une date de début, une date de fin et un nombre de personne"
        DemandeVisite demande = new DemandeVisite(code: code, dateDebutPeriode: debut, dateFinPeriode: fin,
                nbPersonne: nbPersonne, musee: unMusee, statut: statut)

        and: "une date de debut"
        Date now = new Date()

        and: "des dates de fin"
        Date finYears = Calendar.getInstance().set(Calendar.YEAR, Calendar.YEAR+1)
        Date finMonth = Calendar.getInstance().set(Calendar.MONTH, Calendar.MONTH+1)
        Date finDays = Calendar.getInstance().set(Calendar.DAY_OF_YEAR, Calendar.DAY_OF_YEAR+1)

        expect: "Une demande de visite valide"
        demande.validate() == true

        where:
        code | debut   | fin      | nbPersonne | unMusee     | statut
        1    | now     | finYears | 6          | Mock(Musee) | false
        2    | now     | finMonth | 1          | Mock(Musee) | false
        9    | now     | finDays  | 3          | Mock(Musee) | false
        10   | finDays | finMonth | 4          | Mock(Musee) | false
    }

    void "test l'invalidité d'une demande de visite invalide"(int code, Date debut, Date fin, int nbPersonne, Musee unMusee, boolean statut) {

        given: "Une demande de visite"
        DemandeVisite demande = new DemandeVisite(code: code, dateDebutPeriode: debut, dateFinPeriode: fin, nbPersonne: nbPersonne, musee: unMusee, statut: statut)

        and: "une date de debut"
        Date now = new Date()

        and: "des dates de fin"
        Date finYears = Calendar.getInstance().set(Calendar.YEAR, Calendar.YEAR+1)
        Date finMonth = Calendar.getInstance().set(Calendar.MONTH, Calendar.MONTH+1)
        Date finDays = Calendar.getInstance().set(Calendar.DAY_OF_YEAR, Calendar.DAY_OF_YEAR+1)

        expect: "Une demande de visite valide"
        demande.validate() == false

        where:
        code | debut   | fin      | nbPersonne | unMusee     | statut
        -1   | now     | finYears | 6          | Mock(Musee) | false
        2    | now     | finMonth | 1          | Mock(Musee) | true
        9    | now     | finDays  | 7          | Mock(Musee) | false
        10   | finDays | finDays  | 1          | Mock(Musee) | false
        89   | finDays | now      | 1          | Mock(Musee) | false
        1    | now     | finDays  | 1          | null        | false
    }
}
