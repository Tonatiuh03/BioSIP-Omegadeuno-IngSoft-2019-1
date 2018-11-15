package mx.unam.is20191.dao;

import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import mx.unam.is20191.models.Perfil;

/**
 * Clase que implementa las operaciones con la base de datos en lo que respecta
 * a la entidad de perfil.
 *
 * @author Josué Cárdenas
 */
public class PerfilDao extends AbstractDao<Short, Perfil> {

    /**
     * Método que obtiene todos los perfiles disponibles en la base.
     *
     * @return Los perfiles encontrados en la base.
     */
    public List<Perfil> getAll() {
        CriteriaBuilder cb = createCriteriaBuilder();
        CriteriaQuery<Perfil> crit = createCriteriaQuery(cb);
        Root<Perfil> r = createRoot(crit);

        return this.findAll(crit, r, cb.asc(r.get("id")));
    }

}
