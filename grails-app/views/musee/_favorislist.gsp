<g:if test="${session.getAttribute("favoris")}">
    <div class="favoris">
        <p class="titled"><g:message code="favoris.label" default="Favoris" /></p>
        <ul class="list-fav">
            <g:each in="${session.getAttribute("favoris")}" var="musee">
                <li>
                    <span class="fav-label">${musee.value}</span>
                    <g:formRemote id="del_${musee.key}"
                                  name="delFavoris"
                                  onSuccess="delFav(data, ${musee.key})"
                                  url="[controller: 'musee', action: 'deleteFromFavoris']">
                        <g:hiddenField name="museeId" value="${musee.key}" />
                        <g:actionSubmit value="Supprimer" />
                    </g:formRemote>
                </li>
            </g:each>
        </ul>
        <p class="visit">
            <g:link url="[controller: 'demandeVisite', action: 'create']">
                <g:message code="demande.link" default="Effectuer une demande de visite" />
            </g:link>
        </p>
    </div>
</g:if>