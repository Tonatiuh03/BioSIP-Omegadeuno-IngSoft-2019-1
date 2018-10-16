/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var hide = false;

function esconder() {
    var div = $('div.escogerCategoria');
    var subcategoria = $('select.selec-categoria');
    if (hide) {
        div.hide();
        subcategoria.attr("disabled", true);
        subcategoria.attr("required", false);
        hide = false;
    } else {
        div.show();
        subcategoria.attr("disabled", false);
        subcategoria.attr("required", true);
        hide = true;
    }
    
}
