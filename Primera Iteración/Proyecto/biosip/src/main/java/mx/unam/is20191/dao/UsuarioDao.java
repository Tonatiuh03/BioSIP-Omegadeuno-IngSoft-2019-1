package mx.unam.is20191.dao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import mx.unam.is20191.models.Usuario;

/**
 * Clase que implementa las operaciones con la base de datos en lo que respecta
 * a la entidad de usuario.
 *
 * @author Josué Cárdenas
 */
public class UsuarioDao extends AbstractDao<Integer, Usuario> {

    public Usuario searchByUserNameOrEmail(String userOrEmail) {
        CriteriaBuilder cb = createCriteriaBuilder();
        CriteriaQuery<Usuario> crit = createCriteriaQuery(cb);
        Root<Usuario> r = createRoot(crit);
        return searchByExpressionUnique(crit, r, cb.or(cb.equal(r.get("correoCiencias"), userOrEmail),
                cb.equal(r.get("userName"), userOrEmail)));
    }

}
