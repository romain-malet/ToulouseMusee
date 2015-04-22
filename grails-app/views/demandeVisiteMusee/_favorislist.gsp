<g:if test="${session.getAttribute("favoris")}">
	<div class="favoris">
		<p class="titled">
			<g:message code="favoris.label" default="Favoris" />
		</p>
		<ul class="list-fav">
			<g:each in="${session.getAttribute("favoris")}" var="musee">
				<li><span class="fav-label capital">
						${musee.value}
				</span></li>
			</g:each>
		</ul>
	</div>
</g:if>