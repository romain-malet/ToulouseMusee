package toulousemusee



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class DemandeVisiteMuseeController {

	DemandeVisiteMuseeService demandeVisiteMuseeService

	static allowedMethods = [save: "POST", update: "PUT"]

	def list(){
		def demande = demandeVisiteMuseeService.getDemandeVisite(params.code)
		if(demande)
			session.setAttribute("code", demande.id)
		return [demandes: demande]
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

		log.debug musees
		List<DemandeVisiteMusee> demandes = demandeVisiteMuseeService.save(demandeVisite, musees.keySet().asList())

		session.setAttribute("favoris", null)
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
