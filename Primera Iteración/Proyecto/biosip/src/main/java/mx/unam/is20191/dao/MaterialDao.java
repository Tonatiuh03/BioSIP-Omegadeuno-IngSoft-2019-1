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
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import mx.unam.is20191.models.Categoria;
import mx.unam.is20191.models.Material;
import mx.unam.is20191.models.Subcategoria;

/**
 *
 * @author jrcvd
 */
public class MaterialDao extends AbstractDao<Long, Material> {

    public List<Material> searchMaterial(String nombreMaterial, Integer idCategoria, Integer idSubcategoria) {
        CriteriaBuilder cb = createCriteriaBuilder();
        CriteriaQuery<Material> crit = createCriteriaQuery(cb);
        Root<Material> r = createRoot(crit);

        boolean porMaterial = nombreMaterial.isEmpty(),
                porCategoria = idCategoria > -1,
                porSubcategoria = idSubcategoria > -1;
        Predicate busqueda = null;
        ArrayList<Predicate> busquedasOr = new ArrayList<>();
        if (porMaterial) {
            busqueda = cb.like(r.get(nombreMaterial), "%" + nombreMaterial + "%");
            busquedasOr.add(busqueda);
        }
        if (porCategoria) {
            CategoriaDao categoriaDao = new CategoriaDao();
            Categoria cat = categoriaDao.getByKey(idCategoria);
            if (cat != null) {
                busqueda = cb.isMember(categoriaDao.getByKey(idCategoria), r.get("categoriaSet"));
                busquedasOr.add(busqueda);
            }
        }
        if (porSubcategoria) {
            SubcategoriaDao subcategoriaDao = new SubcategoriaDao();
            Subcategoria scat = subcategoriaDao.getByKey(idSubcategoria);
            if (scat != null) {
                busqueda = cb.isMember(scat, r.get("subcategoriaSet"));
                busquedasOr.add(busqueda);
            }

        }
        if (busquedasOr.isEmpty()) {
            return this.findAll(crit, r, cb.asc(r.get("id")));
        } else {
            if (busquedasOr.size() < 2) {
                return this.searchByExpression(crit, r, busqueda, cb.asc(r.get("id")));
            } else {
                return this.searchByExpression(crit, r,
                        cb.or(busquedasOr.toArray(new Predicate[busquedasOr.size()])), cb.asc(r.get("id")));
            }
        }

    }

    public List<Material> getMateriales() {
        CriteriaBuilder cb = createCriteriaBuilder();
        CriteriaQuery<Material> crit = createCriteriaQuery(cb);
        Root<Material> r = createRoot(crit);

        return this.findAll(crit, r, cb.asc(r.get("id")));
    }

}
