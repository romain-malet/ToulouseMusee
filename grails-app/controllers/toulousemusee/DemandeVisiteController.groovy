package toulousemusee



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class DemandeVisiteController {

	static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	DemandeVisiteService demandeVisiteService

	def show(DemandeVisite demandeVisiteInstance) {
		String code = session.getAttribute("code")
		if(code == demandeVisiteInstance?.code){
			def demandes = demandeVisiteService.getDemandeVisiteMusees(demandeVisiteInstance)
			respond demandeVisiteInstance, view:'show', model:['demandesVisitesMusees':demandes]
		
			}
		else
			redirect action:"index"
	}

	def index(){
		if (params.code) {
			log.debug params.code
			DemandeVisite demande = demandeVisiteService.getDemandeVisite(params.code)
			if (!demande)
				params.error = true
			else{
				session.setAttribute("code", params.code)
				show(demande)
			}
		}
		return
	}

	def create() {
		respond new DemandeVisite(params)
	}

	@Transactional
	def save() {
		DemandeVisite demandeVisite = demandeVisiteService.createDemandeVisite(
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
		List<DemandeVisiteMusee> demandes = demandeVisiteService.save(demandeVisite, musees.keySet().asList())

		session.setAttribute("favoris", null)
		respond demandes, view:'create', model:[code:code]
	}

	def edit(DemandeVisite demandeVisiteInstance) {
		respond demandeVisiteInstance
	}

	@Transactional
	def update(DemandeVisite demandeVisiteInstance) {
		if (demandeVisiteInstance == null) {
			notFound()
			return
		}

		if (demandeVisiteInstance.hasErrors()) {
			respond demandeVisiteInstance.errors, view:'edit'
			return
		}

		demandeVisiteInstance.save flush:true

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.updated.message', args: [
					message(code: 'DemandeVisite.label', default: 'DemandeVisite'),
					demandeVisiteInstance.id
				])
				redirect demandeVisiteInstance
			}
			'*'{ respond demandeVisiteInstance, [status: OK] }
		}
	}

	@Transactional
	def delete(DemandeVisite demandeVisiteInstance) {

		if (demandeVisiteInstance == null) {
			notFound()
			return
		}

		demandeVisiteInstance.delete flush:true

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.deleted.message', args: [
					message(code: 'DemandeVisite.label', default: 'DemandeVisite'),
					demandeVisiteInstance.id
				])
				redirect action:"index", method:"GET"
			}
			'*'{ render status: NO_CONTENT }
		}
	}

	protected void notFound() {
		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.not.found.message', args: [
					message(code: 'demandeVisite.label', default: 'DemandeVisite'),
					params.id
				])
				redirect action: "index", method: "GET"
			}
			'*'{ render status: NOT_FOUND }
		}
	}
}
