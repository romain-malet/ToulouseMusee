import toulousemusee.InitialiseDonneesService

class BootStrap {

    InitialiseDonneesService initialiseDonneesService

    def init = { servletContext ->
        initialiseDonneesService.initData()
    }
    def destroy = {
    }
}
