/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.unam.is20191.models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jrcvd
 */
@Entity
@Table(name = "prestamo_material", catalog = "biosip", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PrestamoMaterial.findAll", query = "SELECT p FROM PrestamoMaterial p")
    , @NamedQuery(name = "PrestamoMaterial.findByPrestamoId", query = "SELECT p FROM PrestamoMaterial p WHERE p.prestamoMaterialPK.prestamoId = :prestamoId")
    , @NamedQuery(name = "PrestamoMaterial.findByMaterialId", query = "SELECT p FROM PrestamoMaterial p WHERE p.prestamoMaterialPK.materialId = :materialId")
    , @NamedQuery(name = "PrestamoMaterial.findByElementosPrestados", query = "SELECT p FROM PrestamoMaterial p WHERE p.elementosPrestados = :elementosPrestados")})
public class PrestamoMaterial implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PrestamoMaterialPK prestamoMaterialPK;
    @Basic(optional = false)
    @Column(name = "elementos_prestados", nullable = false)
    private int elementosPrestados;
    @JoinColumn(name = "material_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Material material;
    @JoinColumn(name = "prestamo_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Prestamo prestamo;

    public PrestamoMaterial() {
    }

    public PrestamoMaterial(PrestamoMaterialPK prestamoMaterialPK) {
        this.prestamoMaterialPK = prestamoMaterialPK;
    }

    public PrestamoMaterial(PrestamoMaterialPK prestamoMaterialPK, int elementosPrestados) {
        this.prestamoMaterialPK = prestamoMaterialPK;
        this.elementosPrestados = elementosPrestados;
    }

    public PrestamoMaterial(long prestamoId, long materialId) {
        this.prestamoMaterialPK = new PrestamoMaterialPK(prestamoId, materialId);
    }

    public PrestamoMaterialPK getPrestamoMaterialPK() {
        return prestamoMaterialPK;
    }

    public void setPrestamoMaterialPK(PrestamoMaterialPK prestamoMaterialPK) {
        this.prestamoMaterialPK = prestamoMaterialPK;
    }

    public int getElementosPrestados() {
        return elementosPrestados;
    }

    public void setElementosPrestados(int elementosPrestados) {
        this.elementosPrestados = elementosPrestados;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Prestamo getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(Prestamo prestamo) {
        this.prestamo = prestamo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (prestamoMaterialPK != null ? prestamoMaterialPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrestamoMaterial)) {
            return false;
        }
        PrestamoMaterial other = (PrestamoMaterial) object;
        if ((this.prestamoMaterialPK == null && other.prestamoMaterialPK != null) || (this.prestamoMaterialPK != null && !this.prestamoMaterialPK.equals(other.prestamoMaterialPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.unam.is20191.models.PrestamoMaterial[ prestamoMaterialPK=" + prestamoMaterialPK + " ]";
    }
    
}
