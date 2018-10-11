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
 * @author sds
 */
@Entity
@Table(name = "kit_material", catalog = "biosip", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "KitMaterial.findAll", query = "SELECT k FROM KitMaterial k")
    , @NamedQuery(name = "KitMaterial.findByKitId", query = "SELECT k FROM KitMaterial k WHERE k.kitMaterialPK.kitId = :kitId")
    , @NamedQuery(name = "KitMaterial.findByMaterialId", query = "SELECT k FROM KitMaterial k WHERE k.kitMaterialPK.materialId = :materialId")
    , @NamedQuery(name = "KitMaterial.findByNumElementosRequeridos", query = "SELECT k FROM KitMaterial k WHERE k.numElementosRequeridos = :numElementosRequeridos")})
public class KitMaterial implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected KitMaterialPK kitMaterialPK;
    @Basic(optional = false)
    @Column(name = "num_elementos_requeridos", nullable = false)
    private int numElementosRequeridos;
    @JoinColumn(name = "kit_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Kit kit;
    @JoinColumn(name = "material_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Material material;

    public KitMaterial() {
    }

    public KitMaterial(KitMaterialPK kitMaterialPK) {
        this.kitMaterialPK = kitMaterialPK;
    }

    public KitMaterial(KitMaterialPK kitMaterialPK, int numElementosRequeridos) {
        this.kitMaterialPK = kitMaterialPK;
        this.numElementosRequeridos = numElementosRequeridos;
    }

    public KitMaterial(long kitId, long materialId) {
        this.kitMaterialPK = new KitMaterialPK(kitId, materialId);
    }

    public KitMaterialPK getKitMaterialPK() {
        return kitMaterialPK;
    }

    public void setKitMaterialPK(KitMaterialPK kitMaterialPK) {
        this.kitMaterialPK = kitMaterialPK;
    }

    public int getNumElementosRequeridos() {
        return numElementosRequeridos;
    }

    public void setNumElementosRequeridos(int numElementosRequeridos) {
        this.numElementosRequeridos = numElementosRequeridos;
    }

    public Kit getKit() {
        return kit;
    }

    public void setKit(Kit kit) {
        this.kit = kit;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (kitMaterialPK != null ? kitMaterialPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof KitMaterial)) {
            return false;
        }
        KitMaterial other = (KitMaterial) object;
        if ((this.kitMaterialPK == null && other.kitMaterialPK != null) || (this.kitMaterialPK != null && !this.kitMaterialPK.equals(other.kitMaterialPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.unam.is20191.models.KitMaterial[ kitMaterialPK=" + kitMaterialPK + " ]";
    }
    
}
