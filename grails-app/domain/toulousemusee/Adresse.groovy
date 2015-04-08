package toulousemusee

class Adresse {

	int rue
	String nomRue
	int codePostale
	String ville

	static constraints = {
		rue min:1, max:20
		nomRue blank:false
		codePostale min:31000, max:31999
		ville blank:false
	}
}
