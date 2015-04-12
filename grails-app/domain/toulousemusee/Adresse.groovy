package toulousemusee

class Adresse {

	int numero
	String rue
	int codePostale
	String ville

	static constraints = {
		numero min:1
		rue blank:false
		codePostale min:31000, max:31999
		ville blank:false
	}
	
	String toString(){
		return "$numero $rue\n$codePostale $ville"
	}
}
