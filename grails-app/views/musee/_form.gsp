<%@ page import="toulousemusee.Musee" %>



<div class="fieldcontain ${hasErrors(bean: museeInstance, field: 'nom', 'error')} required">
	<label for="nom">
		<g:message code="musee.nom.label" default="Nom" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nom" required="" value="${museeInstance?.nom}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: museeInstance, field: 'horairesOuverture', 'error')} required">
	<label for="horairesOuverture">
		<g:message code="musee.horairesOuverture.label" default="Horaires Ouverture" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="horairesOuverture" required="" value="${museeInstance?.horairesOuverture}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: museeInstance, field: 'telephone', 'error')} required">
	<label for="telephone">
		<g:message code="musee.telephone.label" default="Telephone" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="telephone" pattern="${museeInstance.constraints.telephone.matches}" required="" value="${museeInstance?.telephone}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: museeInstance, field: 'accesBus', 'error')} ">
	<label for="accesBus">
		<g:message code="musee.accesBus.label" default="Acces Bus" />
		
	</label>
	<g:textField name="accesBus" value="${museeInstance?.accesBus}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: museeInstance, field: 'accesMetro', 'error')} ">
	<label for="accesMetro">
		<g:message code="musee.accesMetro.label" default="Acces Metro" />
		
	</label>
	<g:textField name="accesMetro" value="${museeInstance?.accesMetro}"/>

</div>
<fieldset class="embedded"><legend><g:message code="musee.adresse.label" default="Adresse" /></legend>
<div class="fieldcontain ${hasErrors(bean: museeInstance, field: 'adresse.codePostale', 'error')} required">
	<label for="adresse.codePostale">
		<g:message code="musee.adresse.codePostale.label" default="Code Postale" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="codePostale" type="number" min="31000" max="31999" value="${adresseInstance.codePostale}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: museeInstance, field: 'adresse.id', 'error')} required">
	<label for="adresse.id">
		<g:message code="musee.adresse.id.label" default="Id" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="id" type="number" value="${adresseInstance.id}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: museeInstance, field: 'adresse.numero', 'error')} required">
	<label for="adresse.numero">
		<g:message code="musee.adresse.numero.label" default="Numero" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="numero" type="number" min="1" value="${adresseInstance.numero}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: museeInstance, field: 'adresse.rue', 'error')} required">
	<label for="adresse.rue">
		<g:message code="musee.adresse.rue.label" default="Rue" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="rue" required="" value="${adresseInstance?.rue}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: museeInstance, field: 'adresse.version', 'error')} required">
	<label for="adresse.version">
		<g:message code="musee.adresse.version.label" default="Version" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="version" type="number" value="${adresseInstance.version}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: museeInstance, field: 'adresse.ville', 'error')} required">
	<label for="adresse.ville">
		<g:message code="musee.adresse.ville.label" default="Ville" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="ville" required="" value="${adresseInstance?.ville}"/>

</div>
</fieldset>
<div class="fieldcontain ${hasErrors(bean: museeInstance, field: 'ddeVisitMusee', 'error')} ">
	<label for="ddeVisitMusee">
		<g:message code="musee.ddeVisitMusee.label" default="Dde Visit Musee" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${museeInstance?.ddeVisitMusee?}" var="d">
    <li><g:link controller="demandeVisiteMusee" action="show" id="${d.id}">${d?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="demandeVisiteMusee" action="create" params="['musee.id': museeInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'demandeVisiteMusee.label', default: 'DemandeVisiteMusee')])}</g:link>
</li>
</ul>


</div>

<div class="fieldcontain ${hasErrors(bean: museeInstance, field: 'gestionnaire', 'error')} required">
	<label for="gestionnaire">
		<g:message code="musee.gestionnaire.label" default="Gestionnaire" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="gestionnaire" name="gestionnaire.id" from="${toulousemusee.Gestionnaire.list()}" optionKey="id" required="" value="${museeInstance?.gestionnaire?.id}" class="many-to-one"/>

</div>

