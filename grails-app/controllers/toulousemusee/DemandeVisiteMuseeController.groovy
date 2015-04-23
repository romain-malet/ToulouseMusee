package toulousemusee



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class DemandeVisiteMuseeController {

	DemandeVisiteMuseeService demandeVisiteMuseeService

	static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def index(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		respond DemandeVisiteMusee.list(params), model:[demandeVisiteMuseeInstanceCount: DemandeVisiteMusee.count()]
	}

	def list(){
		def demandes = DemandeVisiteMusee.list()
		return [demandes: demandes,
			demandesCount: demandes.size(),
			]
	}

	def show(DemandeVisiteMusee demandeVisiteMuseeInstance) {
		respond demandeVisiteMuseeInstance
	}

	def create() {
		respond new DemandeVisiteMusee(params)
	}

	@Transactional
	def save() {
		DemandeVisite demandeVisite = demandeVisiteMuseeService.createDemandeVisite(
				params.dateDebutPeriode,
				params.dateFinPeriode,
				params.nbPersonne as int)

		if(!demandeVisite.validate()){
			respond demandeVisite, view:'create', model:["demandeViste": demandeVisite]
			return
		}
		String code = demandeVisite.code
		Map musees = session.getAttribute("favoris")

		List<DemandeVisiteMusee> demandes = demandeVisiteMuseeService.save(demandeVisite, musees.keySet().asList())

		session.setAttribute("favoris", null)
		session.setAttribute('demandes', demandes)
		respond demandes, view:'show', model:[code:code]
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
