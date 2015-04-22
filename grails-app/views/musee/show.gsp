
<%@ page import="toulousemusee.Musee" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'musee.label', default: 'Musee')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-musee" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
            <div class="content">
                <ul>
                    <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                    <li><g:link class="list" action="index"><g:message code="musee.title" /></g:link></li>
                </ul>
            </div>
		</div>
		<div id="show-musee" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list musee">
			
				<g:if test="${museeInstance?.nom}">
				<li class="fieldcontain">
					<span id="nom-label" class="property-label"><g:message code="musee.nom.label" default="Nom" /></span>
					
						<span class="property-value" aria-labelledby="nom-label"><g:fieldValue bean="${museeInstance}" field="nom"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${museeInstance?.horairesOuverture}">
				<li class="fieldcontain">
					<span id="horairesOuverture-label" class="property-label"><g:message code="musee.horairesOuverture.label" default="Horaires Ouverture" /></span>
					
						<span class="property-value" aria-labelledby="horairesOuverture-label"><g:fieldValue bean="${museeInstance}" field="horairesOuverture"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${museeInstance?.telephone}">
				<li class="fieldcontain">
					<span id="telephone-label" class="property-label"><g:message code="musee.telephone.label" default="Telephone" /></span>
					
						<span class="property-value" aria-labelledby="telephone-label"><g:fieldValue bean="${museeInstance}" field="telephone"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${museeInstance?.accesBus}">
				<li class="fieldcontain">
					<span id="accesBus-label" class="property-label"><g:message code="musee.accesBus.label" default="Acces Bus" /></span>
					
						<span class="property-value" aria-labelledby="accesBus-label"><g:fieldValue bean="${museeInstance}" field="accesBus"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${museeInstance?.accesMetro}">
				<li class="fieldcontain">
					<span id="accesMetro-label" class="property-label"><g:message code="musee.accesMetro.label" default="Acces Metro" /></span>
					
						<span class="property-value" aria-labelledby="accesMetro-label"><g:fieldValue bean="${museeInstance}" field="accesMetro"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${museeInstance?.adresse}">
				<li class="fieldcontain">
					<span id="adresse-label" class="property-label"><g:message code="musee.adresse.label" default="Adresse" /></span>
					
						<span class="property-value" aria-labelledby="adresse-label"><g:fieldValue bean="${museeInstance}" field="adresse"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${museeInstance?.ddeVisitMusee}">
				<li class="fieldcontain">
					<span id="ddeVisitMusee-label" class="property-label"><g:message code="musee.ddeVisitMusee.label" default="Dde Visit Musee" /></span>
					
						<g:each in="${museeInstance.ddeVisitMusee}" var="d">
						<span class="property-value" aria-labelledby="ddeVisitMusee-label"><g:link controller="demandeVisiteMusee" action="show" id="${d.id}">${d?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${museeInstance?.gestionnaire}">
				<li class="fieldcontain">
					<span id="gestionnaire-label" class="property-label"><g:message code="musee.gestionnaire.label" default="Gestionnaire" /></span>
					
						<span class="property-value" aria-labelledby="gestionnaire-label"><g:link controller="gestionnaire" action="show" id="${museeInstance?.gestionnaire?.id}">${museeInstance?.gestionnaire?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
		</div>
	</body>
</html>
