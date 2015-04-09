package toulousemusee

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(DemandeVisite)
class DemandeVisiteSpec extends Specification {

    @Unroll
    void "test la validité d'une demande valide"(int code, Date debut, Date fin,
                                                 int nbPersonne, Musee unMusee) {

        given: "Une demande de visite avec un code, une date de début, une date de fin et un nombre de personne"
        DemandeVisite demande = new DemandeVisite(code: code, dateDebutPeriode: debut, dateFinPeriode: fin,
                nbPersonne: nbPersonne, musee: unMusee)

        expect: "Une demande de visite valide"
        demande.validate() == true

        where:
        code | debut      | fin            | nbPersonne | unMusee
        1    | new Date() | new Date() + 1 | 6          | Mock(Musee)
        2    | new Date() | new Date() + 1 | 1          | Mock(Musee)
        9    | new Date() | new Date() + 2 | 3          | Mock(Musee)
        10   | new Date() | new Date() + 1 | 4          | Mock(Musee)
    }

    @Unroll
    void "test l'invalidité d'une demande de visite invalide"(int code, Date debut, Date fin,
                                                              int nbPersonne, Musee unMusee) {

        given: "Une demande de visite"
        DemandeVisite demande = new DemandeVisite(code: code, dateDebutPeriode: debut, dateFinPeriode: fin,
                nbPersonne: nbPersonne, musee: unMusee)

        expect: "Une demande de visite valide"
        demande.validate() == false

        where:
        code | debut      | fin            | nbPersonne | unMusee
        -1   | new Date() | new Date() + 1 | 6          | Mock(Musee)
        8    | new Date() | new Date() + 1 | 7          | Mock(Musee)
        9    | new Date() | new Date() + 1 | 0          | Mock(Musee)
        10   | new Date(2)| new Date(2)    | 1          | Mock(Musee)
        89   | new Date() | new Date() -1  | 1          | Mock(Musee)
        1    | new Date() | new Date()     | 1          | null
    }
}
