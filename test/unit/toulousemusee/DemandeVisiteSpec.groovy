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
    void "test la validité d'une demande valide"(String code, Date debut, Date fin,
                                                 int nbPersonne, String status) {

        given: "Une demande de visite avec code, date de début, date de fin et nombre de personne"
        DemandeVisite demande = new DemandeVisite(code: code, dateDebutPeriode: debut, dateFinPeriode: fin,
                nbPersonne: nbPersonne, statut: status)

        expect: "Une demande de visite valide"
        demande.validate() == true

        where:
        code              | debut      | fin            | nbPersonne | status
        "CODE-1234567"    | new Date() | new Date() + 1 | 6          | "En cours de traitement"
        "CODE-9876543"    | new Date() | new Date() + 1 | 1          | "Confirmée"
        "CODE-7650321"    | new Date() | new Date() + 2 | 3          | "Refusée"
        "CODE-0000001"    | new Date() | new Date() + 1 | 4          | "En cours de traitement"
    }

    @Unroll
    void "test l'invalidité d'une demande de visite invalide"(String code, Date debut, Date fin,
                                                              int nbPersonne, String status) {

        given: "Une demande de visite"
        DemandeVisite demande = new DemandeVisite(code: code, dateDebutPeriode: debut, dateFinPeriode: fin,
                nbPersonne: nbPersonne, statut: status)

        expect: "Une demande de visite valide"
        demande.validate() == false

        where:
        code | debut      | fin            | nbPersonne | status
        "Code-1234567" | new Date() | new Date() + 1 | 6          | "En cours de traitement"
        "CODE-faux"    | new Date() | new Date() + 1 | 6          | "En cours de traitement"
        "CODE1234567"  | new Date() | new Date() + 1 | 6          | "En cours de traitement"
        ""             | new Date() | new Date() + 1 | 6          | "En cours de traitement"
        null           | new Date() | new Date() + 1 | 6          | "En cours de traitement"
        "CODE-1234567" | new Date() | new Date() + 1 | 7          | "En cours de traitement"
        "CODE-1234567" | new Date() | new Date() + 1 | 0          | "En cours de traitement"
        "CODE-1234567" | new Date(2)| new Date(2)    | 1          | "En cours de traitement"
        "CODE-1234567" | new Date() | new Date() -1  | 1          | "En cours de traitement"
        "CODE-1234567" | new Date() | new Date()     | 1          | null
        "CODE-1234567" | new Date() | new Date()     | 1          | "un statut faux"
        "CODE-1234567" | new Date() | new Date()     | 1          | ""
    }
}
