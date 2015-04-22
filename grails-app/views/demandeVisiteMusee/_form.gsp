<%@ page import="toulousemusee.DemandeVisiteMusee"%>



<div
	class="fieldcontain ${hasErrors(bean: demandeVisite, field: 'dateDebutPeriode', 'error')} required">
	<label for="dateDebutPeriode"> <g:message
			code="demandeVisite.dateDebutPeriode.label"
			default="Début de la période" /> <span class="required-indicator">*</span>
	</label>
	<g:datePicker name="dateDebutPeriode" precision="day"
		value="${new Date()}" />

</div>

<div
	class="fieldcontain ${hasErrors(bean: demandeVisite, field: 'dateFinPeriode', 'error')} required">
	<label for="dateFinPeriode"> <g:message
			code="demandeVisite.dateFinPeriode.label" default="Fin de la période" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="dateFinPeriode" precision="day"
		value="${new Date() + 1 }" />

</div>

<div
	class="fieldcontain ${hasErrors(bean: demandeVisiteInstance, field: 'nbPersonne', 'error')} required">
	<label for="Musees"> <g:message
			code="demandeVisite.nbPersonne.label" default="Nombre de personnes" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="nbPersonne" from="${1..6}" class="range" required=""
		value="${fieldValue(bean: demandeVisiteInstance, field: 'nbPersonne')}" />

</div>

