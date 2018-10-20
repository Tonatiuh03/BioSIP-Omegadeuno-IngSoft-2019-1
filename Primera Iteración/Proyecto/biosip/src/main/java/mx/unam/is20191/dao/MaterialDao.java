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
 * Clase que apoya en la manipulación de materiales en la base de datos.
 *
 * @author Josué Cárdenas
 */
public class MaterialDao extends AbstractDao<Long, Material> {

    /**
     * Método que obtiene los materiales a partir de un nombre de material,
     * identificador de categoría y/o identificador de subcategoría.
     *
     * @param nombreMaterial Es el nombre del material a buscar, puede ser
     * vacío.
     * @param idCategoria Es el identificador de la categoría a buscar, si no se
     * requiere se debe mandar un número menor a 0.
     * @param idSubcategoria Es el identificador de la subcategoría a buscar, si
     * no se requiere se debe mandar un número menor a 0.
     * @return La lista de materiales que hagan match con los criterios, si se
     * le pasa una cadena vacía, un identificador de categoría inválido y un
     * identificador de subcategoría inválido, entonces, regresa todos los
     * materiales. Si no hay coincidencias para los criterios entonces regresa
     * una lista vacía.
     */
    public List<Material> searchMaterial(String nombreMaterial, Integer idCategoria, Integer idSubcategoria) {
        CriteriaBuilder cb = createCriteriaBuilder();
        CriteriaQuery<Material> crit = createCriteriaQuery(cb);
        Root<Material> r = createRoot(crit);

        boolean porMaterial = nombreMaterial.isEmpty(),
                porCategoria = idCategoria > -1,
                porSubcategoria = idSubcategoria > -1;
        Predicate busqueda = null;
        ArrayList<Predicate> busquedasAnd = new ArrayList<>();
        if (porMaterial) {
            busqueda = cb.like(r.get(nombreMaterial), "%" + nombreMaterial + "%");
            busquedasAnd.add(busqueda);
        }
        if (porCategoria) {
            CategoriaDao categoriaDao = new CategoriaDao();
            Categoria cat = categoriaDao.getByKey(idCategoria);
            if (cat != null) {
                busqueda = cb.isMember(categoriaDao.getByKey(idCategoria), r.get("categoriaSet"));
                busquedasAnd.add(busqueda);
            }
        }
        if (porSubcategoria) {
            SubcategoriaDao subcategoriaDao = new SubcategoriaDao();
            Subcategoria scat = subcategoriaDao.getByKey(idSubcategoria);
            if (scat != null) {
                busqueda = cb.isMember(scat, r.get("subcategoriaSet"));
                busquedasAnd.add(busqueda);
            }

        }
        if (busquedasAnd.isEmpty()) {
            return this.findAll(crit, r, cb.asc(r.get("id")));
        } else {
            if (busquedasAnd.size() < 2) {
                return this.searchByExpression(crit, r, busqueda, cb.asc(r.get("id")));
            } else {
                return this.searchByExpression(crit, r,
                        cb.and(busquedasAnd.toArray(new Predicate[busquedasAnd.size()])), cb.asc(r.get("id")));
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
