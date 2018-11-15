package mx.unam.is20191.dao;

import java.util.List;
import mx.unam.is20191.models.Categoria;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * Clase que implementa el manejo de datos con las categorías.
 *
 * @author Josué Rodrigo Cárdenas Vallarta
 */
public class CategoriaDao extends AbstractDao<Integer, Categoria> {

    /**
     * Método que revisa si existe una categoría.
     *
     * @param categoria Es el nombre de la categoría a buscar.
     * @return True si la categoría existe y False en otro caso.
     */
    public boolean categoriaExist(String categoria) {
        CriteriaBuilder cb = createCriteriaBuilder();
        CriteriaQuery<Categoria> crit = createCriteriaQuery(cb);
        Root<Categoria> r = createRoot(crit);

        categoria = categoria.trim();
        return this.count(cb.equal(r.get("nombre"), categoria)) > 0;
    }

    /**
     * Método que obtiene todas las categorías en la base de datos.
     *
     * @return Las categorías encontradas en la base.
     */
    public List<Categoria> getCategorias() {
        CriteriaBuilder cb = createCriteriaBuilder();
        CriteriaQuery<Categoria> crit = createCriteriaQuery(cb);
        Root<Categoria> r = createRoot(crit);

        return this.findAll(crit, r, cb.asc(r.get("id")));
    }
}
