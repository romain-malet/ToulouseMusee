<g:formRemote id="form_${id}"
              name="addFavoris"
              onSuccess="addFav(data, ${id})"
              url="[controller: 'musee', action: 'addToFavoris']">
    <g:hiddenField name="museeNom" value="${nom}" />
    <g:hiddenField name="museeId" value="${id}" />
    <g:actionSubmit class="add-fav" value="Ajouter à ma liste de musées" />
</g:formRemote>