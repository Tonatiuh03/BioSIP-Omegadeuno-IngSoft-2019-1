/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.unam.is20191.dao;

import java.util.List;
import mx.unam.is20191.models.Subcategoria;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author dams_
 */
public class SubcategoriaDao extends AbstractDao<Integer, Subcategoria>{

    public boolean categoriaExist(String subcategoria) {
        CriteriaBuilder cb = createCriteriaBuilder();
        CriteriaQuery<Subcategoria> crit = createCriteriaQuery(cb);
        Root<Subcategoria> r = createRoot(crit);

        subcategoria = subcategoria.trim();
        return this.count(cb.equal(r.get("nombre"), subcategoria)) > 0;
    }

    public List<Subcategoria> getSubcategorias(){      
        CriteriaBuilder cb = createCriteriaBuilder();
        CriteriaQuery<Subcategoria> crit = createCriteriaQuery(cb);
        Root<Subcategoria> r = createRoot(crit);

        return this.findAll(crit, r, cb.asc(r.get("id")));
    }
}
