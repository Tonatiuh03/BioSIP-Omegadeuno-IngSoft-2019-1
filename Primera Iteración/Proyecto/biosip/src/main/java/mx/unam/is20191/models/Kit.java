/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.unam.is20191.models;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jrcvd
 */
@Entity
@Table(name = "kit")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Kit.findAll", query = "SELECT k FROM Kit k")
    , @NamedQuery(name = "Kit.findById", query = "SELECT k FROM Kit k WHERE k.id = :id")
    , @NamedQuery(name = "Kit.findByFechaDeExpiracion", query = "SELECT k FROM Kit k WHERE k.fechaDeExpiracion = :fechaDeExpiracion")})
public class Kit implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "fecha_de_expiracion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDeExpiracion;
    @JoinColumn(name = "usuario_id_autor", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario usuarioIdAutor;
    @JoinColumn(name = "administrador_id_aprobador", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario administradorIdAprobador;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "kit", fetch = FetchType.LAZY)
    private Set<KitMaterial> kitMaterialSet;

    public Kit() {
    }

    public Kit(Long id) {
        this.id = id;
    }

    public Kit(Long id, Date fechaDeExpiracion) {
        this.id = id;
        this.fechaDeExpiracion = fechaDeExpiracion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaDeExpiracion() {
        return fechaDeExpiracion;
    }

    public void setFechaDeExpiracion(Date fechaDeExpiracion) {
        this.fechaDeExpiracion = fechaDeExpiracion;
    }

    public Usuario getUsuarioIdAutor() {
        return usuarioIdAutor;
    }

    public void setUsuarioIdAutor(Usuario usuarioIdAutor) {
        this.usuarioIdAutor = usuarioIdAutor;
    }

    public Usuario getAdministradorIdAprobador() {
        return administradorIdAprobador;
    }

    public void setAdministradorIdAprobador(Usuario administradorIdAprobador) {
        this.administradorIdAprobador = administradorIdAprobador;
    }

    @XmlTransient
    public Set<KitMaterial> getKitMaterialSet() {
        return kitMaterialSet;
    }

    public void setKitMaterialSet(Set<KitMaterial> kitMaterialSet) {
        this.kitMaterialSet = kitMaterialSet;
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
        if (!(object instanceof Kit)) {
            return false;
        }
        Kit other = (Kit) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.unam.is20191.models.Kit[ id=" + id + " ]";
    }
    
}
