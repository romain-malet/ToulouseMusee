<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main">
<g:set var="entityName"
	value="${message(code: 'demandeVisiteMusee.label', default: 'DemandeVisiteMusee')}" />
<title><g:message code="default.create.label"
		args="[entityName]" /></title>
</head>
<body>
	<a href="#create-demandeVisiteMusee" class="skip" tabindex="-1"><g:message
			code="default.link.skip.label" default="Skip to content&hellip;" /></a>
	<div class="nav" role="navigation">
		<ul>
			<li><a class="home" href="${createLink(uri: '/')}"><g:message
						code="default.home.label" /></a></li>
		</ul>
	</div>
	<div id="create-demandeVisiteMusee" class="content scaffold-create"
		role="main">
		<h1>
			<g:message code="default.create.label" args="[entityName]" />
		</h1>
		<g:if test="${flash.message}">
			<div class="message" role="status">
				${flash.message}
			</div>
		</g:if>

		<g:hasErrors bean="${demandeVisiteInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${demandeVisiteInstance}" var="error">
					<li
						<g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
							error="${error}" /></li>
				</g:eachError>
			</ul>
		</g:hasErrors>
		<g:if test="${session.getAttribute("favoris")}">
			<g:form url="[resource:demandeVisiteMuseeInstance, action:'save']">
				<fieldset class="form">
					<g:render template="form" />
				</fieldset>
				<div class="field-inline">
					<g:actionSubmit class="add-fav" action="save"
						value="${message(code: 'default.button.create.label', default: 'Create')}" />
				</div>
			</g:form>
			<div id="favoris">
				<g:render template="favorislist" />
			</div>
		</g:if>
		<g:else>
			<div>
				<g:message code="demandeViste.noFavoris.label" />
			</div>
		</g:else>
	</div>
</body>
</html>
