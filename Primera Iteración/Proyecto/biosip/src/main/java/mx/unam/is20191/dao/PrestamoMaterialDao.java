/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.unam.is20191.dao;

import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import mx.unam.is20191.models.Material;
import mx.unam.is20191.models.Prestamo;
import mx.unam.is20191.models.PrestamoMaterial;
import mx.unam.is20191.models.PrestamoMaterialPK;

/**
 *
 * @author dams_
 */
public class PrestamoMaterialDao extends AbstractDao<PrestamoMaterialPK, PrestamoMaterial>{
    public List<PrestamoMaterial> getRegistros() {
        CriteriaBuilder cb = createCriteriaBuilder();
        CriteriaQuery<PrestamoMaterial> crit = createCriteriaQuery(cb);
        Root<PrestamoMaterial> r = createRoot(crit);
        return this.findAll(crit, r, cb.asc(r.get("prestamoMaterialPK")));
    }    
    
    public List<Pair<Material, Integer>> getMateriales(Prestamo p){
        MaterialDao md = new MaterialDao();
        ArrayList<Pair<Material, Integer>> lm = new ArrayList();
        for (PrestamoMaterial mp : this.getRegistros()){
            if(mp.getPrestamo().getId() == (p.getId())){
                lm.add(new Pair(mp.getMaterial(), mp.getElementosPrestados()));
            }
        }
        return lm;
    }
}
