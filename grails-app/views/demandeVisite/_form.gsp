<%@ page import="toulousemusee.DemandeVisite" %>



<div class="fieldcontain ${hasErrors(bean: demandeVisiteInstance, field: 'code', 'error')} required">
	<label for="code">
		<g:message code="demandeVisite.code.label" default="Code" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="code" pattern="${demandeVisiteInstance.constraints.code.matches}" required="" value="${demandeVisiteInstance?.code}"/>

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

<div class="fieldcontain ${hasErrors(bean: demandeVisiteInstance, field: 'statut', 'error')} required">
	<label for="statut">
		<g:message code="demandeVisite.statut.label" default="Statut" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="statut" from="${demandeVisiteInstance.constraints.statut.inList}" required="" value="${demandeVisiteInstance?.statut}" valueMessagePrefix="demandeVisite.statut"/>

</div>

<div class="fieldcontain ${hasErrors(bean: demandeVisiteInstance, field: 'demandesVisitesMusees', 'error')} ">
	<label for="demandesVisitesMusees">
		<g:message code="demandeVisite.demandesVisitesMusees.label" default="Demandes Visites Musees" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${demandeVisiteInstance?.demandesVisitesMusees?}" var="d">
    <li><g:link controller="demandeVisiteMusee" action="show" id="${d.id}">${d?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="demandeVisiteMusee" action="create" params="['demandeVisite.id': demandeVisiteInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'demandeVisiteMusee.label', default: 'DemandeVisiteMusee')])}</g:link>
</li>
</ul>


</div>

<div class="fieldcontain ${hasErrors(bean: demandeVisiteInstance, field: 'musees', 'error')} ">
	<label for="musees">
		<g:message code="demandeVisite.musees.label" default="Musees" />
		
	</label>
	<g:select name="musees" from="${toulousemusee.Musee.list()}" multiple="multiple" optionKey="id" size="5" value="${demandeVisiteInstance?.musees*.id}" class="many-to-many"/>

</div>

