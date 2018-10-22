package mx.unam.is20191.dao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import mx.unam.is20191.models.Material;

/**
 * Clase que implementa las operaciones con la base de datos en lo que respecta
 * a la entidad de Material.
 *
 * @author Nora Hernández
 */
public class Dao extends AbstractDao<Integer, Material> {

    public Material searchByUserNameOrEmail(String userOrEmail) {
        CriteriaBuilder cb = createCriteriaBuilder();
        CriteriaQuery<Material> crit = createCriteriaQuery(cb);
        Root<Material> r = createRoot(crit);
        return searchByExpressionUnique(crit, r, cb.or(cb.equal(r.get("correoCiencias"), userOrEmail),
                cb.equal(r.get("userName"), userOrEmail)));
    }

    public boolean mailExist(String email) {
        CriteriaBuilder cb = createCriteriaBuilder();
        CriteriaQuery<Material> crit = createCriteriaQuery(cb);
        Root<Material> r = createRoot(crit);
        return this.count(cb.equal(r.get("correoCiencias"), email)) > 0;
    }

    public boolean userExist(String user) {
        CriteriaBuilder cb = createCriteriaBuilder();
        CriteriaQuery<Material> crit = createCriteriaQuery(cb);
        Root<Material> r = createRoot(crit);
        return this.count(cb.equal(r.get("userName"), user)) > 0;
    }

}
