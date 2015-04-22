
<%@ page import="toulousemusee.DemandeVisiteMusee"%>
<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main">
<g:set var="entityName"
	value="${message(code: 'demandeVisiteMusee.label', default: 'DemandeVisiteMusee')}" />
<title><g:message code="default.show.label" args="[entityName]" /></title>
</head>
<body>
	<a href="#show-demandeVisiteMusee" class="skip" tabindex="-1"><g:message
			code="default.link.skip.label" default="Skip to content&hellip;" /></a>
	<div class="nav" role="navigation">
		<ul>
			<li><a class="home" href="${createLink(uri: '/')}"><g:message
						code="default.home.label" /></a></li>
		</ul>
	</div>
	<div></div>
	<div class="listing-musee" class="content scaffold-list" role="main">
		<p>Vos demandes de vistes ont été bien envoyées et seront traitées
			prochainement.</p>
		<p>
			Voici vos codes :
			${codes}
		</p>
	</div>
</body>
</html>
