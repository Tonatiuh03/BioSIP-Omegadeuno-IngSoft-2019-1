/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.unam.is20191.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import mx.unam.is20191.models.Material;
import mx.unam.is20191.models.Prestamo;
import mx.unam.is20191.models.PrestamoMaterial;
import mx.unam.is20191.models.Usuario;

/**
 *
 * @author dams_
 */
public class PrestamoDao extends AbstractDao<Long, Prestamo> {

    public List<Prestamo> getPrestamos() {
        CriteriaBuilder cb = createCriteriaBuilder();
        CriteriaQuery<Prestamo> crit = createCriteriaQuery(cb);
        Root<Prestamo> r = createRoot(crit);

        return this.findAll(crit, r, cb.desc(r.get("id")));
    }

    public List<Prestamo> getPrestamos(Usuario usuario) {
        CriteriaBuilder cb = createCriteriaBuilder();
        CriteriaQuery<Prestamo> crit = createCriteriaQuery(cb);
        Root<Prestamo> r = createRoot(crit);
        ArrayList<Prestamo> reservaciones = new ArrayList<Prestamo>();
        for(Prestamo prestamo: this.findAll(crit, r, cb.desc(r.get("id")))){
            if(prestamo.getUsuarioId().getId().equals(usuario.getId())){
                reservaciones.add(prestamo);
            }
        }
        return reservaciones;//this.findAll(crit, r, cb.desc(r.get("id")== usuario.getId()));
    }

}
