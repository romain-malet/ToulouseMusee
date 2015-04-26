
<%@ page import="toulousemusee.DemandeVisite"%>
<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main">
<g:set var="entityName"
	value="${message(code: 'demandeVisite.label', default: 'DemandeVisite')}" />
<title><g:message code="default.show.label" args="[entityName]" /></title>
</head>
<body>
	<a href="#show-demandeVisite" class="skip" tabindex="-1"><g:message
			code="default.link.skip.label" default="Skip to content&hellip;" /></a>
	<div class="nav" role="navigation">
		<div class="content">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message
                            code="default.home.label" /></a></li>
            </ul>
        </div>
	</div>
	<div id="show-demandeVisite" class="content scaffold-show" role="main">
		<h1>
			<g:message code="default.show.label" args="[entityName]" />
		</h1>
		<g:if test="${flash.message}">
			<div class="message" role="status">
				${flash.message}
			</div>
		</g:if>
		<ol class="property-list demandeVisite">

			<g:if test="${demandeVisiteInstance?.code}">
				<li class="fieldcontain"><span id="code-label"
					class="property-label"><g:message
							code="demandeVisite.code.label" default="Code" /></span> <span
					class="property-value" aria-labelledby="code-label"><g:fieldValue
							bean="${demandeVisiteInstance}" field="code" /></span></li>
			</g:if>
			<g:if test="${demandeVisiteInstance?.nbPersonne}">
				<li class="fieldcontain"><span id="nbPersonne-label"
					class="property-label"><g:message
							code="demandeVisite.nbPersonne.label" default="Nb Personne" /></span> <span
					class="property-value" aria-labelledby="nbPersonne-label"><g:fieldValue
							bean="${demandeVisiteInstance}" field="nbPersonne" /></span></li>
			</g:if>

			<g:if test="${demandeVisiteInstance?.statut}">
				<li class="fieldcontain"><span id="statut-label"
					class="property-label"><g:message
							code="demandeVisite.statut.label" default="Statut" /></span> <span
					class="property-value" aria-labelledby="statut-label"><g:fieldValue
							bean="${demandeVisiteInstance}" field="statut" /></span></li>
			</g:if>

				<li class="fieldcontain"><span id="demandesVisitesMusees-label"
					class="property-label"><g:message
							code="demandeVisite.musees.label"
							default="Musées" /></span>
						<g:each in="${demandesVisitesMusees.musee}" var="m">
							<span class="property-value" aria-labelledby="Musee-label"><g:link
									controller="Musee" action="show" id="${m.id}">
									${m?.encodeAsHTML()}
								</g:link><br/><br/></span>
						</g:each></li>
			<g:if test="${demandeVisiteInstance.statut == "Confirmée"}">
				<li class="fieldcontain"><span id="dateDebutPeriode-label"
					class="property-label"><g:message
							code="demandeVisite.dateDebutPeriode.label"
							default="Date Debut Periode" /></span> <span class="property-value"
					aria-labelledby="dateDebutPeriode-label"><g:formatDate format="dd MM yyyy"
							date="${demandeVisiteInstance?.dateDebutPeriode}" /></span></li>

				<li class="fieldcontain"><span id="dateFinPeriode-label"
					class="property-label"><g:message
							code="demandeVisite.dateFinPeriode.label"
							default="Date Fin Periode" /></span> <span class="property-value"
					aria-labelledby="dateFinPeriode-label"><g:formatDate
							date="${demandeVisiteInstance?.dateFinPeriode}" /></span></li>
			</g:if>
			<g:elseif test="${demandeVisiteInstance.statut == "Refusée"}">
				<p>
					<g:message code="demandeVisite.noGuide"
						default="Aucun guide disponible sur cette période" />
				</p>
			</g:elseif>
		</ol>
	</div>
</body>
</html>
