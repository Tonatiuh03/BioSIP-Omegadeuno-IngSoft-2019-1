/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var cambio = true;

function esconder() {
    if (cambio) {
        $('.escogerMateriales').hide();
        $('.confirmarMateriales').show();
        cambio = !cambio;
    } else {
        $('.escogerMateriales').show();
        $('.confirmarMateriales').hide();
        cambio = !cambio;
    }
}



