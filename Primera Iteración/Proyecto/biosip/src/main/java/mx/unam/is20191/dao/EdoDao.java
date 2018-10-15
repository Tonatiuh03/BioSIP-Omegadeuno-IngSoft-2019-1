package mx.unam.is20191.dao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import mx.unam.is20191.models.Edo;

/**
 * Clase que implementa las operaciones con la base de datos en lo que respecta
 * a la entidad de Edo.
 *
 * @author Nora Hernández
 */
public class EdoDao extends AbstractDao<Integer, Edo> {

    public Edo searchByUserNameOrEmail(String userOrEmail) {
        CriteriaBuilder cb = createCriteriaBuilder();
        CriteriaQuery<Edo> crit = createCriteriaQuery(cb);
        Root<Edo> r = createRoot(crit);
        return searchByExpressionUnique(crit, r, cb.or(cb.equal(r.get("correoCiencias"), userOrEmail),
                cb.equal(r.get("userName"), userOrEmail)));
    }

    public boolean mailExist(String email) {
        CriteriaBuilder cb = createCriteriaBuilder();
        CriteriaQuery<Edo> crit = createCriteriaQuery(cb);
        Root<Edo> r = createRoot(crit);
        return this.count(cb.equal(r.get("correoCiencias"), email)) > 0;
    }

    public boolean userExist(String user) {
        CriteriaBuilder cb = createCriteriaBuilder();
        CriteriaQuery<Edo> crit = createCriteriaQuery(cb);
        Root<Edo> r = createRoot(crit);
        return this.count(cb.equal(r.get("userName"), user)) > 0;
    }

}
