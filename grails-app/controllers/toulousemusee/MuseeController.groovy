package toulousemusee

import grails.gorm.PagedResultList

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class MuseeController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    MuseeService museeService

    def search() {
        def nom = params.nom ?: ""
        def cp = params.codePostale ?: 0
        def rue = params.rue ?: ""
        redirect(action: 'index', params: [nom: nom, cp: cp, rue: rue])
    }

    def index() {
        def codePostaux = museeService.postalCode()
        def offset = params.offset ?: 0
        if (request.queryString) {
            PagedResultList museeList = museeService.search(params.nom, params.cp as int, params.rue, offset as int, 5)
            return [museeInstanceList: museeList,
                    museeInstanceCount: museeList.getTotalCount(),
                    showMusee: true,
                    codes: codePostaux,
                    param: params]
        }
        else {
            return [codes: codePostaux,
                    showMusee: false,
                    param: params]
        }
    }

    def addToFavoris() {
        Map favorisList = session.getAttribute('favoris')
        if (request.post) {
            if (!favorisList) {
                favorisList = new HashMap()
            }
            favorisList.put(params.museeId as long, params.museeNom)
            favorisList.sort {it.value}
            session.setAttribute('favoris', favorisList)
        }
        render template: "favorislist"
    }

    def deleteFromFavoris() {
        Map favorisList = session.getAttribute('favoris')
        if (request.post) {
            favorisList.remove(params.museeId as long)
            session.setAttribute('favoris', favorisList)
        }
        render template: "favorislist"
    }

    def formFav() {
        def musee = Musee.findById(params.id as long)
        render template: "formAddFavoris", model: [id: params.id, nom: musee.nom]
    }

    def show(Musee museeInstance) {
        respond museeInstance
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'musee.label', default: 'Musee'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
