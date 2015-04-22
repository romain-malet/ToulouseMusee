package toulousemusee



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class DemandeVisiteMuseeController {

	static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def index(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		respond DemandeVisiteMusee.list(params), model:[demandeVisiteMuseeInstanceCount: DemandeVisiteMusee.count()]
	}

	def show(DemandeVisiteMusee demandeVisiteMuseeInstance) {
		respond demandeVisiteMuseeInstance
	}

	def create() {
		respond new DemandeVisiteMusee(params)
	}

	@Transactional
	def save() {
		def musees = session.getAttribute("favoris")
		Date date = new Date()
		Map codes = new HashMap<Musee, String>()
		for (long museeId : musees.keySet()){
			Musee musee = Musee.get(museeId)
			DemandeVisite demandeVisite = new DemandeVisite(params.dateDebutPeriode,
					params.dateFinPeriode, params.nbPersonne as int)
			if(!demandeVisite.validate()){
				respond demandeVisite, view:'create', model:["demandeViste": demandeVisite]
				return
			}
			demandeVisite.save(flush:true, failOnError: true)
			codes.put(musee, demandeVisite.code)
			DemandeVisiteMusee demandeVisiteMusee = new DemandeVisiteMusee(demandeVisite:demandeVisite,
			musee:musee, dateDemande:date)
		}

		demandeVisiteMusee.save flush:true

		respond DemandeVisiteMuseeInstance, view:'show', model:[codes:codes]
	}

	def edit(DemandeVisiteMusee demandeVisiteMuseeInstance) {
		respond demandeVisiteMuseeInstance
	}

	protected void notFound() {
		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.not.found.message', args: [
					message(code: 'demandeVisiteMusee.label', default: 'DemandeVisiteMusee'),
					params.id
				])
				redirect action: "index", method: "GET"
			}
			'*'{ render status: NOT_FOUND }
		}
	}
}
