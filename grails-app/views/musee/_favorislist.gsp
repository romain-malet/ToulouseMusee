<g:if test="${session.getAttribute("favoris")}">
    <div class="favoris">
        <p class="titled">Mes musées préférés</p>
        <ul class="list-fav">
            <g:each in="${session.getAttribute("favoris")}" var="musee">
                <li>${musee.value}</li>
            </g:each>
        </ul>
    </div>
</g:if>