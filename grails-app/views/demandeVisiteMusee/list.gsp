
<%@ page import="toulousemusee.DemandeVisiteMusee"%>
<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main">
<g:set var="entityName"
	value="${message(code: 'demandeVisiteMusee.label', default: 'Demande de visite')}" />
<title><g:message code="musee.title" /></title>
</head>
<body>
	<a href="#list-musee" class="skip" tabindex="-1"><g:message
			code="default.link.skip.label" default="Skip to content&hellip;" /></a>
	<div class="nav" role="navigation">
		<div class="content">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message
							code="default.home.label" /></a></li>
			</ul>
		</div>
	</div>
	<div id="list-musee" class="content scaffold-list" role="main">
		<h1>
			<g:message code="musee.title" />
		</h1>
		<g:if test="${flash.message}">
			<div class="message" role="status">
				${flash.message}
			</div>
		</g:if>
		<g:form>
			<fieldset class="form">
				<div class="field-inline">
					<label for="nom"> <g:message
							code="demandeVisiteMusee.form.code.label" default="Code :" />
					</label>
					<g:textField name="nom" value="${params.code}" />
				</div>
				<div class="field-inline">
					<g:actionSubmit class="add-fav" action="list" value="Get" />
				</div>
			</fieldset>
		</g:form>
		<g:if test="${demandes}">
			<p class="num-result">
				${demandesCount}
				<g:message code="demandeVisiteMusee.resultat.label"
					default="résultats" />
			</p>
			<g:each in="${demandes}" status="i" var="demandeInstance">
				<div class="listing-musee">
					<strong><g:message
							code="demandeVisite.code.label"
							default="Code : " /></strong>
						<g:link controller="demandeVisite" action="show"
							id="${demandeInstance.demandeVisite.id}">
							${fieldValue(bean: demandeInstance.demandeVisite, field: "code")}
						</g:link>
					<p>
						<strong><g:message
								code="demandeVisite.dateDebutPeriode.label"
								default="Début de la période : " /></strong>
						${fieldValue(bean: demandeInstance.demandeVisite, field: "dateDebutPeriode")}
					</p>
					<p>
						<strong><g:message
								code="demandeVisite.dateFinPeriode.label"
								default="Fin de la période : " /></strong>
						${fieldValue(bean: demandeInstance.demandeVisite, field: "dateFinPeriode")}
					</p>
					<p>
						<strong><g:message code="musee.label" default="Musée : " /></strong>
						${fieldValue(bean: demandeInstance.musee, field: "nom")}
					</p>
					<p>
						<strong><g:message code="demandeVisite.statut.label"
								default="Statut : " /></strong>
						${fieldValue(bean: demandeInstance.demandeVisite, field: "statut")}
					</p>
				</div>
			</g:each>
		</g:if>
	</div>
</body>
</html>
