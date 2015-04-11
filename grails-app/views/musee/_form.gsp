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

<div class="fieldcontain ${hasErrors(bean: museeInstance, field: 'accesBus', 'error')} required">
	<label for="accesBus">
		<g:message code="musee.accesBus.label" default="Acces Bus" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="accesBus" required="" value="${museeInstance?.accesBus}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: museeInstance, field: 'accesMetro', 'error')} required">
	<label for="accesMetro">
		<g:message code="musee.accesMetro.label" default="Acces Metro" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="accesMetro" required="" value="${museeInstance?.accesMetro}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: museeInstance, field: 'adresse', 'error')} required">
	<label for="adresse">
		<g:message code="musee.adresse.label" default="Adresse" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="adresse" name="adresse.id" from="${toulousemusee.Adresse.list()}" optionKey="id" required="" value="${museeInstance?.adresse?.id}" class="many-to-one"/>

</div>

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

