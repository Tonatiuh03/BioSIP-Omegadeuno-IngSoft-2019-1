package mx.unam.is20191.dao;

import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import mx.unam.is20191.models.Usuario;
import mx.unam.is20191.utils.Rol;

/**
 * Clase que implementa las operaciones con la base de datos en lo que respecta
 * a la entidad de usuario.
 *
 * @author Josué Cárdenas
 */
public class UsuarioDao extends AbstractDao<Long, Usuario> {

    /**
     * Método que busca a un usuario en la base por correo o por email.
     *
     * @param userOrEmail Es el correo o el email del usuario.
     * @return El usuario que se encontró en la base de datos o, en caso de no
     * encontrarse, regresa un null.
     */
    public Usuario searchByUserNameOrEmail(String userOrEmail) {
        CriteriaBuilder cb = createCriteriaBuilder();
        CriteriaQuery<Usuario> crit = createCriteriaQuery(cb);
        Root<Usuario> r = createRoot(crit);
        return searchByExpressionUnique(crit, r, cb.or(cb.equal(r.get("correoCiencias"), userOrEmail),
                cb.equal(r.get("userName"), userOrEmail)));
    }

    /**
     * Método que obtiene a un Usuario a partir del código de confirmación que
     * se le pasa como argumento.
     *
     * @param confirmacion Es el código de confirmación del usuario a buscar.
     * @return El usuario que coincida con el código de confirmación o null si
     * no lo encontró.
     */
    public Usuario searchByConfirmacion(String confirmacion) {
        CriteriaBuilder cb = createCriteriaBuilder();
        CriteriaQuery<Usuario> crit = createCriteriaQuery(cb);
        Root<Usuario> r = createRoot(crit);
        return searchByExpressionUnique(crit, r, cb.equal(r.get("confirmacion").get("token"), confirmacion));
    }

    /**
     * Método que revisa si un mail existe en la base.
     *
     * @param email Es el email a buscar en la base.
     * @return True si existe ya en la base y false en otro caso.
     */
    public boolean mailExist(String email) {
        CriteriaBuilder cb = createCriteriaBuilder();
        CriteriaQuery<Usuario> crit = createCriteriaQuery(cb);
        Root<Usuario> r = createRoot(crit);
        return this.count(cb.equal(r.get("correoCiencias"), email)) > 0;
    }

    /**
     * Método que revisa si un usuario existe en la base de datos.
     *
     * @param user Es el usuario a buscar en la base.
     * @return True si es que existe y false en otro caso.
     */
    public boolean userExist(String user) {
        CriteriaBuilder cb = createCriteriaBuilder();
        CriteriaQuery<Usuario> crit = createCriteriaQuery(cb);
        Root<Usuario> r = createRoot(crit);
        return this.count(cb.equal(r.get("userName"), user)) > 0;
    }

    /**
     * Método que verifica si un usuario tiene permiso de administrador.
     *
     * @param userManager Es el usuario a evaluar.
     * @return True si es administrador y false en otro caso.
     */
    public static boolean isAdmin(Usuario userManager) {
        return userManager.getPerfilSet().stream().anyMatch((p) -> (p.getNombre().equals(Rol.ADMINISTRADOR)));
    }

    /**
     * Método que verifica si un usuario tiene permiso de profesor.
     *
     * @param userManager Es el usuario a evaluar.
     * @return True si es profesor y false en otro caso.
     */
    public static boolean isProfesor(Usuario userManager) {
        return userManager.getPerfilSet().stream().anyMatch((p) -> (p.getNombre().equals(Rol.PROFESOR)));
    }

    /**
     * Método que regresa todos los usuarios en el sistema.
     *
     * @return La lista que contiene a todos los usuarios encontrados.
     */
    public List<Usuario> getAll() {
        CriteriaBuilder cb = createCriteriaBuilder();
        CriteriaQuery<Usuario> crit = createCriteriaQuery(cb);
        Root<Usuario> r = createRoot(crit);

        return this.findAll(crit, r, cb.asc(r.get("nombreCompleto")));
    }

}
