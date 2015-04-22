
<%@ page import="toulousemusee.Musee"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="${resource(dir: 'css', file: 'style.css')}"
	type="text/css">
<meta name="layout" content="main">
<g:set var="entityName"
	value="${message(code: 'musee.label', default: 'Musee')}" />
<title><g:message code="default.list.label" args="[entityName]" /></title>
</head>
<body>
	<a href="#list-musee" class="skip" tabindex="-1"><g:message
			code="default.link.skip.label" default="Skip to content&hellip;" /></a>
	<div class="nav" role="navigation">
		<ul>
			<li><a class="home" href="${createLink(uri: '/')}"><g:message
						code="default.home.label" /></a></li>
		</ul>
	</div>
	<div id="list-musee" class="content scaffold-list" role="main">
		<h1>
			<g:message code="default.list.label" args="[entityName]" />
		</h1>
		<g:if test="${flash.message}">
			<div class="message" role="status">
				${flash.message}
			</div>
		</g:if>
		<g:form>
			<fieldset class="searchForm">
				<div>
					<label for="nom" class="searchLabel"> Nom du musée contient : </label>
					<g:textField name="nom" value="${param.nom}" class="searchInput"/>
					<label for="rue" class="searchLabel"> La rue du musée contient : </label>
					<g:textField name="rue" value="${param.nom}" class="searchInput"/>
					<label for="codePostale" class="searchLabel"> Code postal : </label>
					<g:select name="codePostale" from="${codes}" value="${param.cp}"
						noSelection="${['0':'Choisir...']}" class="searchInput"/>
					<g:actionSubmit action="search"
							value="Rechercher" class="searchButton"/>
				</div>
			</fieldset>
		</g:form>
		<g:if test="${showMusee}">
			<p style="text-align: center; font-weight: 600; margin-bottom: 1em">
				${museeInstanceCount}
				résulta(s)
			</p>
			<g:each in="${museeInstanceList}" status="i" var="museeInstance">
				<div
					style="margin: 0 2em 2em; padding: 1em; box-shadow: 0 0 5px #aaa">
					<h2>
						<g:link action="show" id="${museeInstance.id}">
							${fieldValue(bean: museeInstance, field: "nom")}
						</g:link>
					</h2>
					<p>
						<strong><g:message code="musee.horairesOuverture.label"
								default="Horaires" /></strong>
						${fieldValue(bean: museeInstance, field: "horairesOuverture")}
					</p>
					<p>
						<strong><g:message code="musee.telephone.label"
								default="Téléphone" /></strong>
						${fieldValue(bean: museeInstance, field: "telephone")}
					</p>
					<p>
						<strong><g:message code="musee.accesBus.label"
								default="Accès bus" /></strong>
						${fieldValue(bean: museeInstance, field: "accesBus")}
					</p>
					<p>
						<strong><g:message code="musee.accesMetro.label"
								default="Accès métro" /></strong>
						${fieldValue(bean: museeInstance, field: "accesMetro")}
					</p>
					<p>
						<strong><g:message code="musee.adresse.label"
								default="Adresse" /></strong>
						${fieldValue(bean: museeInstance, field: "adresse")}
					</p>
					<p>
						<strong><g:message code="musee.gestionnaire.label"
								default="Gestionnaire" /></strong>
						${fieldValue(bean: museeInstance, field: "gestionnaire")}
					</p>
					<g:if test="${museeInstanceCount > 2}">
						<div style="float: right">
							<g:form url="[action: 'listeMuseePreferes']">
								<g:actionSubmit name="addToMusee"
									value="Ajouter à ma liste de musées" />
							</g:form>
						</div>
					</g:if>
				</div>
			</g:each>
			<div class="pagination">
				<g:paginate total="${museeInstanceCount ?: 0}" max="5" maxsteps="5"
					params="${[nom: param.nom, cp: param.cp, rue: param.rue]}" />
			</div>
		</g:if>
	</div>
</body>
</html>
