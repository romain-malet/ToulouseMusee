/**
 * Created by Romain on 21/04/2015.
 */

function addFav(data, id) {
    $('#favoris').html(data);
    $('#form_' + id).remove();
    $('#actionFav_' + id).html(
        '<input id="added_'+ id +'" disabled="disabled" type="button" value="Ajouter à ma liste de musées"/>');
}

function delFav(data, id) {
    $('#favoris').html(data);
    $('#added_' + id).remove();
    $.ajax({
        url: '/ToulouseMusee/musee/formFav',
        type: 'POST',
        data: {id: id}
    }).success(function(data){
        $('#actionFav_' + id).html(data);
    });
}