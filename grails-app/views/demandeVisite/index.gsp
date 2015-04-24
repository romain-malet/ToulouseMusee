
<%@ page import="toulousemusee.DemandeVisite"%>
<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main">
<g:set var="entityName"
	value="${message(code: 'demandeVisite.label', default: 'Demande de visite')}" />
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
			<g:message code="demandeVisite.title" default="Consulter l'état d'une demande de visite" />
		</h1>
		<g:if test="${flash.message}">
			<div class="message" role="status">
				${flash.message}
			</div>
		</g:if>
		<g:if test="${params.error}">
			<ul role="alert" class="errors">
				<li data-field-id="code"><g:message
						code="demandeVisite.codeError" default="Le code n'est pas valide!" />
				</li>
			</ul>
		</g:if>
		<g:form>
			<fieldset class="form">
				<div class="field-inline">
					<label for="nom"> <g:message
							code="demandeVisite.form.code.label" default="Code :" />
					</label>
					<g:textField name="code" value="${params.code}" />
				</div>
				<div class="field-inline">
					<g:actionSubmit class="add-fav" action="index" value="Get" />
				</div>
			</fieldset>
		</g:form>
		<g:if test="${false}">
			<div class="listing-musee">
				<p>
					<strong><g:message code="demandeVisite.statut.label"
							default="Statut : " /></strong>
					${fieldValue(bean: demandes[0].demandeVisite, field: "statut")}
				</p>
				<strong><g:message code="musees.label" default="Musée(s)" />
					: </strong>
				<ol>
					<g:each in="${demandes}" status="i"
						var="demandeVisite">
						<li>
							${demandeVisite.musee.nom}
						</li>
					</g:each>
				</ol>
				<g:if test="${demandes[0].demandeVisite.statut == "Confirmée"}">
					<p>
						<strong><g:message
								code="demandeVisite.dateDebutPeriode.label"
								default="Début de la période : " /></strong>
						${fieldValue(bean: demandes[0].demandeVisite, field: "dateDebutPeriode")}
					</p>
					<p>
						<strong><g:message
								code="demandeVisite.dateFinPeriode.label"
								default="Fin de la période : " /></strong>
						${fieldValue(bean: demandes[0].demandeVisite, field: "dateFinPeriode")}
					</p>
				</g:if>
				<g:elseif test="${demandes[0].demandeVisite.statut == "Refusée"}">
					<p>
						<g:message code="demandeVisite.noGuide"
							default="Aucun guide disponible sur cette période" />
					</p>
				</g:elseif>
			</div>
		</g:if>
	</div>
</body>
</html>
