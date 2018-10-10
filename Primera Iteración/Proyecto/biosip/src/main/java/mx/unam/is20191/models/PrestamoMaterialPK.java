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
 * @author jrcvd
 */
@Embeddable
public class PrestamoMaterialPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "prestamo_id", nullable = false)
    private long prestamoId;
    @Basic(optional = false)
    @Column(name = "material_id", nullable = false)
    private long materialId;

    public PrestamoMaterialPK() {
    }

    public PrestamoMaterialPK(long prestamoId, long materialId) {
        this.prestamoId = prestamoId;
        this.materialId = materialId;
    }

    public long getPrestamoId() {
        return prestamoId;
    }

    public void setPrestamoId(long prestamoId) {
        this.prestamoId = prestamoId;
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
        hash += (int) prestamoId;
        hash += (int) materialId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrestamoMaterialPK)) {
            return false;
        }
        PrestamoMaterialPK other = (PrestamoMaterialPK) object;
        if (this.prestamoId != other.prestamoId) {
            return false;
        }
        if (this.materialId != other.materialId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.unam.is20191.models.PrestamoMaterialPK[ prestamoId=" + prestamoId + ", materialId=" + materialId + " ]";
    }
    
}
