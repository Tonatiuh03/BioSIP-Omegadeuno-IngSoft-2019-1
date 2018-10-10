/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.unam.is20191.models;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jrcvd
 */
@Entity
@Table(name = "material", catalog = "biosip", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Material.findAll", query = "SELECT m FROM Material m")
    , @NamedQuery(name = "Material.findById", query = "SELECT m FROM Material m WHERE m.id = :id")
    , @NamedQuery(name = "Material.findByNombre", query = "SELECT m FROM Material m WHERE m.nombre = :nombre")
    , @NamedQuery(name = "Material.findByDisponibles", query = "SELECT m FROM Material m WHERE m.disponibles = :disponibles")
    , @NamedQuery(name = "Material.findByRutaImagen", query = "SELECT m FROM Material m WHERE m.rutaImagen = :rutaImagen")
    , @NamedQuery(name = "Material.findByDescripcion", query = "SELECT m FROM Material m WHERE m.descripcion = :descripcion")})
public class Material implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic(optional = false)
    @Column(name = "nombre", nullable = false, length = 200)
    private String nombre;
    @Basic(optional = false)
    @Column(name = "disponibles", nullable = false)
    private int disponibles;
    @Column(name = "ruta_imagen", length = 100)
    private String rutaImagen;
    @Basic(optional = false)
    @Column(name = "descripcion", nullable = false, length = 500)
    private String descripcion;
    @JoinTable(name = "material_subcategoria", joinColumns = {
        @JoinColumn(name = "material_id", referencedColumnName = "id", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "subcategoria_id", referencedColumnName = "id", nullable = false)})
    @ManyToMany
    private Collection<Subcategoria> subcategoriaCollection;
    @JoinTable(name = "material_categoria", joinColumns = {
        @JoinColumn(name = "material_id", referencedColumnName = "id", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "categoria_id", referencedColumnName = "id", nullable = false)})
    @ManyToMany
    private Collection<Categoria> categoriaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "material")
    private Collection<PrestamoMaterial> prestamoMaterialCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "material")
    private Collection<KitMaterial> kitMaterialCollection;

    public Material() {
    }

    public Material(Long id) {
        this.id = id;
    }

    public Material(Long id, String nombre, int disponibles, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.disponibles = disponibles;
        this.descripcion = descripcion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDisponibles() {
        return disponibles;
    }

    public void setDisponibles(int disponibles) {
        this.disponibles = disponibles;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public Collection<Subcategoria> getSubcategoriaCollection() {
        return subcategoriaCollection;
    }

    public void setSubcategoriaCollection(Collection<Subcategoria> subcategoriaCollection) {
        this.subcategoriaCollection = subcategoriaCollection;
    }

    @XmlTransient
    public Collection<Categoria> getCategoriaCollection() {
        return categoriaCollection;
    }

    public void setCategoriaCollection(Collection<Categoria> categoriaCollection) {
        this.categoriaCollection = categoriaCollection;
    }

    @XmlTransient
    public Collection<PrestamoMaterial> getPrestamoMaterialCollection() {
        return prestamoMaterialCollection;
    }

    public void setPrestamoMaterialCollection(Collection<PrestamoMaterial> prestamoMaterialCollection) {
        this.prestamoMaterialCollection = prestamoMaterialCollection;
    }

    @XmlTransient
    public Collection<KitMaterial> getKitMaterialCollection() {
        return kitMaterialCollection;
    }

    public void setKitMaterialCollection(Collection<KitMaterial> kitMaterialCollection) {
        this.kitMaterialCollection = kitMaterialCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Material)) {
            return false;
        }
        Material other = (Material) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.unam.is20191.models.Material[ id=" + id + " ]";
    }
    
}
