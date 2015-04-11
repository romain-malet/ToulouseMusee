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
            while ((nextLine = csv.readNext()) != null) {
                Gestionnaire unGestionnaire = Gestionnaire.findByName(nextLine[1])
                if (!unGestionnaire) {
                    unGestionnaire = new Gestionnaire(name: nextLine[1])
                }
                Adresse uneAdresse = new Adresse(
                        numero: nextLine[7] as int,
                        rue: nextLine[8],
                        codePostale: nextLine[9] as int,
                        ville: nextLine[10],
                )
                Musee unMusee = new Musee(
                        nom: nextLine[0],
                        horairesOuverture: nextLine[2],
                        telephone: nextLine[4],
                        accesBus: nextLine[6],
                        accesMetro: nextLine[5],
                        adresse: uneAdresse
                )
                museeService.insertOrUpdate(unMusee, unGestionnaire)
            }
            csv.close()
        }
    }
}
