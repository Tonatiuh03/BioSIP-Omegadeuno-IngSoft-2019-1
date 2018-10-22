/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.unam.is20191.dao;

import java.util.List;
import mx.unam.is20191.models.Kit;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import mx.unam.is20191.models.KitMaterial;

/**
 *
 * @author fjtfj
 */
public class KitDao extends AbstractDao<Integer, Kit> {

    public boolean kitExist(String kit) {
        CriteriaBuilder cb = createCriteriaBuilder();
        CriteriaQuery<Kit> crit = createCriteriaQuery(cb);
        Root<Kit> r = createRoot(crit);

        kit = kit.trim();
        return this.count(cb.equal(r.get("nombre"), kit)) > 0;
    }

    public List<Kit> getKits() {
        CriteriaBuilder cb = createCriteriaBuilder();
        CriteriaQuery<Kit> crit = createCriteriaQuery(cb);
        Root<Kit> r = createRoot(crit);

        return this.findAll(crit, r, cb.asc(r.get("id")));
    }

    public void save(KitMaterial kit) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}


