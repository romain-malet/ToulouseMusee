package toulousemusee

class HomeController {

	def index() {
		redirect(controller: 'musee', action: 'index')
	}
}