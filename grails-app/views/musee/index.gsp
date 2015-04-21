
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
                            <g:message code="musee.form.nom.label" default="Nom du musée :" />
                        </label>
                        <g:textField name="nom" value="${param.nom}"/>
                        <label for="rue">
                            <g:message code="musee.form.rue.label" default="Rue du musée :" />
                        </label>
                        <g:textField name="rue" value="${param.rue}"/>
                    </div>
                    <div class="fieldcontain">
                        <label for="codePostale">
                            <g:message code="musee.form.code.label" default="Code postal :" />
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
                <p class="num-result">${museeInstanceCount} <g:message code="musee.resultat.label" default="résultats" /></p>
				<g:each in="${museeInstanceList}" status="i" var="museeInstance">
					<div class="listing-musee">
                        <h2>
                            <g:link action="show" id="${museeInstance.id}">${fieldValue(bean: museeInstance, field: "nom")}</g:link>
                        </h2>
                        <p>
                            <strong><g:message code="musee.horairesOuverture.label" default="Horaires" /></strong>
                            ${fieldValue(bean: museeInstance, field: "horairesOuverture")}
                        </p>
                        <p>
                            <strong><g:message code="musee.telephone.label" default="Téléphone" /></strong>
                            ${fieldValue(bean: museeInstance, field: "telephone")}
                        </p>
                        <p>
                            <strong><g:message code="musee.accesBus.label" default="Accès bus" /></strong>
                            ${fieldValue(bean: museeInstance, field: "accesBus")}
                        </p>
                        <p>
                            <strong><g:message code="musee.accesMetro.label" default="Accès métro" /></strong>
                            ${fieldValue(bean: museeInstance, field: "accesMetro")}
                        </p>
                        <p>
                            <strong><g:message code="musee.adresse.label" default="Adresse" /></strong>
                            ${fieldValue(bean: museeInstance, field: "adresse")}
                        </p>
                        <p>
                            <strong><g:message code="musee.gestionnaire.label" default="Gestionnaire" /></strong>
                            ${fieldValue(bean: museeInstance, field: "gestionnaire")}
                        </p>
                        <g:if test="${museeInstanceCount > 2}">
                            <div id="actionFav_${museeInstance.id}" style="float: right">
                                <g:if test="${session.getAttribute("favoris") && session.getAttribute("favoris").get(museeInstance.id)}">
                                    <input id="added_${museeInstance.id}" disabled="disabled" type="button" value="Ajouter à ma liste de musées"/>
                                </g:if>
                                <g:else>
                                    <g:render template="formAddFavoris" model="['id': museeInstance.id, 'nom': fieldValue(bean: museeInstance, field: 'nom')]" />
                                </g:else>
                            </div>
                        </g:if>
                    </div>
				</g:each>
			<div class="pagination">
				<g:paginate total="${museeInstanceCount ?: 0}" max="5" params="${[nom: param.nom, cp: param.cp, rue: param.rue]}" />
			</div>
            </g:if>
		</div>
        <div id="favoris">
            <g:render template="favorislist"/>
        </div>
	</body>
</html>
