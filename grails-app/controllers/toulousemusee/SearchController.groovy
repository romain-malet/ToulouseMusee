package toulousemusee

class SearchController {

	def index() {
		def codes = getCodes()
		if (params.codePostale){
			codes.remove(codes.indexOf(Integer.valueOf(params.codePostale)))
			codes.add(0, params.codePostale)
		}
		if (!params.nom?.trim() && !params.codePostale?.trim() && !params.rue?.trim()) {
			return [codes:codes, searchCount:0]
		}
		Set musees = Musee.list()
		Set results = new HashSet()
		for(Musee musee : musees){
			if(params.nom?.trim() && musee.nom.toLowerCase().contains(params.nom.toLowerCase().trim())) {
				results.add(musee)
			}
			if(params.rue?.trim() && musee.adresse.rue.toLowerCase().contains(params.rue.toLowerCase().trim())) {
				results.add(musee)
			}
			if(params.codePostale?.trim() && musee.adresse.codePostale == (params.codePostale.trim() as int)) {
				results.add(musee)
			}
		}
		return [results:results, params:params, codes:codes, searchCount:results.size()]
	}

	def getCodes(){
		Set musees = Musee.list()
		Set codes = new HashSet()
		for(Musee musee : musees){
			codes.add(musee.adresse.codePostale)
		}
		return new ArrayList(codes)
	}
}
