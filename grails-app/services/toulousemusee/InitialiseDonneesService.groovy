package toulousemusee

import grails.transaction.Transactional
import liquibase.util.csv.opencsv.CSVReader

@Transactional
class InitialiseDonneesService {

    MuseeService museeService

    def initData() {
        if (Musee.count() == 0 && Gestionnaire.count() == 0) {
            CSVReader csv = new CSVReader(new FileReader("grails-app/conf/musee.csv"), (char)';')
            String [] nextLine;
            csv.readNext()
            while ((nextLine = csv.readNext())) {
                Gestionnaire unGestionnaire = Gestionnaire.findByName(nextLine[1])
                if (!unGestionnaire) {
                    unGestionnaire  = new Gestionnaire(nom: nextLine[1])
                }
                Adresse uneAdresse = new Adresse(
                        numero: nextLine[7],
                        rue: nextLine[8],
                        codePostale: nextLine[9],
                        ville: nextLine[10],
                )
                museeService.insertOrUpdate(new Musee(
                        nom: nextLine[0],
                        horairesOuverture: nextLine[2],
                        telephone: nextLine[4],
                        accesBus: nextLine[6],
                        accesMetro: nextLine[5],
                        gestionnaire: unGestionnaire,
                        adresse: uneAdresse
                ))
            }
            csv.close()
        }
    }
}
