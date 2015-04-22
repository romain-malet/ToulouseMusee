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

	def show(DemandeVisiteMusee demandeVisiteMuseeInstance) {
		respond demandeVisiteMuseeInstance
	}

	def create() {
		respond new DemandeVisiteMusee(params)
	}

	@Transactional
	def save() {
		String code = demandeVisiteMuseeService.getNextCode()
		DemandeVisite demandeVisite = new DemandeVisite(
			code:code,
			dateDebutPeriode:params.dateDebutPeriode,
			dateFinPeriode:params.dateFinPeriode,
			nbPersonne: params.nbPersonne as int)
		
		if(!demandeVisite.validate()){
			respond demandeVisite, view:'create', model:["demandeViste": demandeVisite]
			return
		}
		Map musees = session.getAttribute("favoris")

		List<DemandeVisiteMusee> demandes = demandeVisiteMuseeService.save(params.dateDebutPeriode,
			 params.dateFinPeriode, params.nbPersonne as int, musees.keySet())

		session.setAttribute('demandes', demandes)
		respond demandes, view:'show', model:[codes:demandes]
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
