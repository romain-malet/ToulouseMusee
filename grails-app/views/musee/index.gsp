
<%@ page import="toulousemusee.Musee" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'musee.label', default: 'Musee')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-musee" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
			</ul>
		</div>
		<div id="list-musee" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
            <g:form>
                <fieldset class="form">
                    <div class="fieldcontain">
                        <label for="nom">
                            Nom du musée contient :
                        </label>
                        <g:textField name="nom" value="${param.nom}"/>
                        <label for="rue">
                            La rue du musée contient :
                        </label>
                        <g:textField name="rue" value="${param.rue}"/>
                    </div>
                    <div class="fieldcontain">
                        <label for="codePostale">
                            Code postal :
                        </label>
                        <g:select name="codePostale"
                                  from="${codes}"
                                  value="${param.cp}"
                                  noSelection="${['0':'Choisir...']}"/>
                    </div>
                    <div style="float: right">
                        <g:actionSubmit action="search" value="Rechercher" />
                    </div>
                </fieldset>
            </g:form>
            <g:if test="${showMusee}">
                <p style="text-align: center;font-weight: 600">${museeInstanceCount} résulta(s)</p>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="nom" title="${message(code: 'musee.nom.label', default: 'Nom')}" />
					
						<g:sortableColumn property="horairesOuverture" title="${message(code: 'musee.horairesOuverture.label', default: 'Horaires Ouverture')}" />
					
						<g:sortableColumn property="telephone" title="${message(code: 'musee.telephone.label', default: 'Telephone')}" />
					
						<g:sortableColumn property="accesBus" title="${message(code: 'musee.accesBus.label', default: 'Acces Bus')}" />
					
						<g:sortableColumn property="accesMetro" title="${message(code: 'musee.accesMetro.label', default: 'Acces Metro')}" />
					
						<th><g:message code="musee.adresse.label" default="Adresse" /></th>

                        <th><g:message code="musee.gestionnaire.label" default="Gestionnaire" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${museeInstanceList}" status="i" var="museeInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${museeInstance.id}">${fieldValue(bean: museeInstance, field: "nom")}</g:link></td>
					
						<td>${fieldValue(bean: museeInstance, field: "horairesOuverture")}</td>
					
						<td>${fieldValue(bean: museeInstance, field: "telephone")}</td>
					
						<td>${fieldValue(bean: museeInstance, field: "accesBus")}</td>
					
						<td>${fieldValue(bean: museeInstance, field: "accesMetro")}</td>
					
						<td>${fieldValue(bean: museeInstance, field: "adresse")}</td>

                        <td>${fieldValue(bean: museeInstance, field: "gestionnaire")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${museeInstanceCount ?: 0}" max="5" maxsteps="5" params="${[nom: param.nom, cp: param.cp, rue: param.rue]}" />
			</div>
            </g:if>
		</div>
	</body>
</html>
