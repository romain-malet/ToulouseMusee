<%@ page import="toulousemusee.DemandeVisite" %>



<div class="fieldcontain ${hasErrors(bean: demandeVisiteInstance, field: 'code', 'error')} required">
	<label for="code">
		<g:message code="demandeVisite.code.label" default="Code" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="code" type="number" min="1" value="${demandeVisiteInstance.code}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: demandeVisiteInstance, field: 'dateDebutPeriode', 'error')} required">
	<label for="dateDebutPeriode">
		<g:message code="demandeVisite.dateDebutPeriode.label" default="Date Debut Periode" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="dateDebutPeriode" precision="day"  value="${demandeVisiteInstance?.dateDebutPeriode}"  />

</div>

<div class="fieldcontain ${hasErrors(bean: demandeVisiteInstance, field: 'dateFinPeriode', 'error')} required">
	<label for="dateFinPeriode">
		<g:message code="demandeVisite.dateFinPeriode.label" default="Date Fin Periode" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="dateFinPeriode" precision="day"  value="${demandeVisiteInstance?.dateFinPeriode}"  />

</div>

<div class="fieldcontain ${hasErrors(bean: demandeVisiteInstance, field: 'nbPersonne', 'error')} required">
	<label for="nbPersonne">
		<g:message code="demandeVisite.nbPersonne.label" default="Nb Personne" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="nbPersonne" from="${1..6}" class="range" required="" value="${fieldValue(bean: demandeVisiteInstance, field: 'nbPersonne')}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: demandeVisiteInstance, field: 'ddeVisitMusee', 'error')} ">
	<label for="ddeVisitMusee">
		<g:message code="demandeVisite.ddeVisitMusee.label" default="Dde Visit Musee" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${demandeVisiteInstance?.ddeVisitMusee?}" var="d">
    <li><g:link controller="demandeVisiteMusee" action="show" id="${d.id}">${d?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="demandeVisiteMusee" action="create" params="['demandeVisite.id': demandeVisiteInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'demandeVisiteMusee.label', default: 'DemandeVisiteMusee')])}</g:link>
</li>
</ul>


</div>

<div class="fieldcontain ${hasErrors(bean: demandeVisiteInstance, field: 'musee', 'error')} required">
	<label for="musee">
		<g:message code="demandeVisite.musee.label" default="Musee" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="musee" name="musee.id" from="${toulousemusee.Musee.list()}" optionKey="id" required="" value="${demandeVisiteInstance?.musee?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: demandeVisiteInstance, field: 'statut', 'error')} ">
	<label for="statut">
		<g:message code="demandeVisite.statut.label" default="Statut" />
		
	</label>
	<g:checkBox name="statut" value="${demandeVisiteInstance?.statut}" />

</div>

