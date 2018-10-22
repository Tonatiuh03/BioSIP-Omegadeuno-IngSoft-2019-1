/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.unam.is20191.dao;

import java.util.List;
import mx.unam.is20191.models.Categoria;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author jrcvd
 */
public class CategoriaDao extends AbstractDao<Integer, Categoria> {

    public boolean categoriaExist(String categoria) {
        CriteriaBuilder cb = createCriteriaBuilder();
        CriteriaQuery<Categoria> crit = createCriteriaQuery(cb);
        Root<Categoria> r = createRoot(crit);

        categoria = categoria.trim();
        return this.count(cb.equal(r.get("nombre"), categoria)) > 0;
    }

    public List<Categoria> getCategorias() {
        CriteriaBuilder cb = createCriteriaBuilder();
        CriteriaQuery<Categoria> crit = createCriteriaQuery(cb);
        Root<Categoria> r = createRoot(crit);

        return this.findAll(crit, r, cb.asc(r.get("id")));
    }
}
