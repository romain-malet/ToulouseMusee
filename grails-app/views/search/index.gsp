<%@ page defaultCodec="html"%>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<title>Accueil</title>
<style type="text/css">
* {
	font-family: Arial, sans-serif;
	padding: 0;
	margin: 0;
}

body {
	font-size: 0.9em;
	padding: .5em;
}

#header form input {
	padding: .1em;
}

#header .hint {
	color: gray;
}

#header h1 a {
	text-decoration: none;
	font-family: Georgia, serif;
	color: dimgray;
}

#header h1 {
	letter-spacing: -0.1em;
	float: left;
}

#header h1 span {
	font-family: Georgia, serif;
	color: #424242;
}

#header form {
	margin-left: 22em;
	padding-top: .1em;
}

.title {
	margin: 1em 0;
	padding: .3em .5em;
	text-align: right;
	background-color: seashell;
	border-top: 1px solid lightblue;
}

.result {
	margin-bottom: 1em;
}

.result .displayLink {
	color: green;
}

.result .name {
	font-size: larger;
}

.paging a.step {
	padding: 0 .3em;
}

.paging span.currentStep {
	font-weight: bold;
}

ul {
	margin: 1em 2em;
}

li, p {
	margin-bottom: 1em;
}
</style>
</head>
<body>
	<div id="header">
		<g:form url='[controller: "search", action: "index"]'
			id="searchableForm" name="searchableForm" method="get">
			<table>
				<tr>
					<td>Nom:</td>
					<td><g:textField name="nom" value="${params.nom}" size="50" /></td>
				</tr>
				<tr>
					<td>Code postale:</td>
					<td><g:select id="codePostale" name="codePostale"
							from="${codes}" /></td>
				</tr>
				<tr>
					<td>Rue:</td>
					<td><g:textField name="rue" value="${params.rue}" size="50" /></td>
				</tr>
			</table>
			<input type="submit" value="Search" />
		</g:form>
	</div>
	<div>
		<g:if test="${results && !results.isEmpty()}">
			<table>
				<thead>
					<tr>
						<th>Nom</th>
						<th>Horaires d'ouverture</th>
						<th>Téléphone</th>
						<th>Accès Bus</th>
						<th>Accès Metro</th>
						<th>Adresse</th>
					</tr>
				</thead>
				<tbody>
					<g:each in="${results}" status="i" var="result">
						<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

							<td><g:link resource="musee" action="show" id="${result.id}">
									${fieldValue(bean: result, field: "nom")}
								</g:link></td>

							<td>
								${fieldValue(bean: result, field: "horairesOuverture")}
							</td>

							<td>
								${fieldValue(bean: result, field: "telephone")}
							</td>

							<td>
								${fieldValue(bean: result, field: "accesBus")}
							</td>

							<td>
								${fieldValue(bean: result, field: "accesMetro")}
							</td>

							<td>
								${fieldValue(bean: result, field: "adresse")}
							</td>

						</tr>
					</g:each>
				</tbody>
			</table>
		</g:if>
	</div>
	<g:paginate next="Forward" prev="Back" max="5" controller="search"
		action="index" total="${searchCount}"
		params="[nom: params.nom,rue: params.rue, codePostale: params.codePostale]" />
</body>
</html>
