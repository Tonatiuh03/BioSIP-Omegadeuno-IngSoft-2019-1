/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.unam.is20191.models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author sds
 */
@Embeddable
public class KitMaterialPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "kit_id", nullable = false)
    private long kitId;
    @Basic(optional = false)
    @Column(name = "material_id", nullable = false)
    private long materialId;

    public KitMaterialPK() {
    }

    public KitMaterialPK(long kitId, long materialId) {
        this.kitId = kitId;
        this.materialId = materialId;
    }

    public long getKitId() {
        return kitId;
    }

    public void setKitId(long kitId) {
        this.kitId = kitId;
    }

    public long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(long materialId) {
        this.materialId = materialId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) kitId;
        hash += (int) materialId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof KitMaterialPK)) {
            return false;
        }
        KitMaterialPK other = (KitMaterialPK) object;
        if (this.kitId != other.kitId) {
            return false;
        }
        if (this.materialId != other.materialId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.unam.is20191.models.KitMaterialPK[ kitId=" + kitId + ", materialId=" + materialId + " ]";
    }
    
}
