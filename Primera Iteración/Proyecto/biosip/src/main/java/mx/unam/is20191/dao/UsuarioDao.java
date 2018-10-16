package mx.unam.is20191.dao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import mx.unam.is20191.models.Perfil;
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

    public Usuario searchByConfirmacion(String confirmacion) {
        CriteriaBuilder cb = createCriteriaBuilder();
        CriteriaQuery<Usuario> crit = createCriteriaQuery(cb);
        Root<Usuario> r = createRoot(crit);
        return searchByExpressionUnique(crit, r, cb.equal(r.get("confirmacion").get("token"), confirmacion));
    }

    public boolean mailExist(String email) {
        CriteriaBuilder cb = createCriteriaBuilder();
        CriteriaQuery<Usuario> crit = createCriteriaQuery(cb);
        Root<Usuario> r = createRoot(crit);
        return this.count(cb.equal(r.get("correoCiencias"), email)) > 0;
    }

    public boolean userExist(String user) {
        CriteriaBuilder cb = createCriteriaBuilder();
        CriteriaQuery<Usuario> crit = createCriteriaQuery(cb);
        Root<Usuario> r = createRoot(crit);
        return this.count(cb.equal(r.get("userName"), user)) > 0;
    }

    public static boolean isAdmin(Usuario u) {
        for (Perfil p : u.getPerfilSet()) {
            if (p.getNombre().equals("ADMIN")) {
                return true;
            }
        }
        return false;
    }

}
