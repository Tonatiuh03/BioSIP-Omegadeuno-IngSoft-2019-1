/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.unam.is20191.dao;

import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import mx.unam.is20191.models.Material;
import mx.unam.is20191.models.Prestamo;
import mx.unam.is20191.models.PrestamoMaterial;

/**
 *
 * @author dams_
 */
public class PrestamoDao extends AbstractDao<Integer, Prestamo> {

    public List<Prestamo> getPrestamos() {
        CriteriaBuilder cb = createCriteriaBuilder();
        CriteriaQuery<Prestamo> crit = createCriteriaQuery(cb);
        Root<Prestamo> r = createRoot(crit);

        return this.findAll(crit, r, cb.asc(r.get("id")));
    }

}
