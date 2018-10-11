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
 * @author sds
 */
@Entity
@Table(catalog = "biosip", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Prestamo.findAll", query = "SELECT p FROM Prestamo p")
    , @NamedQuery(name = "Prestamo.findById", query = "SELECT p FROM Prestamo p WHERE p.id = :id")
    , @NamedQuery(name = "Prestamo.findByFechaDeSolicitud", query = "SELECT p FROM Prestamo p WHERE p.fechaDeSolicitud = :fechaDeSolicitud")
    , @NamedQuery(name = "Prestamo.findByFechaDeAprobacion", query = "SELECT p FROM Prestamo p WHERE p.fechaDeAprobacion = :fechaDeAprobacion")
    , @NamedQuery(name = "Prestamo.findByFechaDeDevolucion", query = "SELECT p FROM Prestamo p WHERE p.fechaDeDevolucion = :fechaDeDevolucion")})
public class Prestamo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(nullable = false)
    private Long id;
    @Basic(optional = false)
    @Column(name = "fecha_de_solicitud", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDeSolicitud;
    @Column(name = "fecha_de_aprobacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDeAprobacion;
    @Column(name = "fecha_de_devolucion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDeDevolucion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "prestamo", fetch = FetchType.LAZY)
    private Set<PrestamoMaterial> prestamoMaterialSet;
    @JoinColumn(name = "usuario_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario usuarioId;
    @JoinColumn(name = "administrador_id_aprobador", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario administradorIdAprobador;

    public Prestamo() {
    }

    public Prestamo(Long id) {
        this.id = id;
    }

    public Prestamo(Long id, Date fechaDeSolicitud) {
        this.id = id;
        this.fechaDeSolicitud = fechaDeSolicitud;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaDeSolicitud() {
        return fechaDeSolicitud;
    }

    public void setFechaDeSolicitud(Date fechaDeSolicitud) {
        this.fechaDeSolicitud = fechaDeSolicitud;
    }

    public Date getFechaDeAprobacion() {
        return fechaDeAprobacion;
    }

    public void setFechaDeAprobacion(Date fechaDeAprobacion) {
        this.fechaDeAprobacion = fechaDeAprobacion;
    }

    public Date getFechaDeDevolucion() {
        return fechaDeDevolucion;
    }

    public void setFechaDeDevolucion(Date fechaDeDevolucion) {
        this.fechaDeDevolucion = fechaDeDevolucion;
    }

    @XmlTransient
    public Set<PrestamoMaterial> getPrestamoMaterialSet() {
        return prestamoMaterialSet;
    }

    public void setPrestamoMaterialSet(Set<PrestamoMaterial> prestamoMaterialSet) {
        this.prestamoMaterialSet = prestamoMaterialSet;
    }

    public Usuario getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Usuario usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Usuario getAdministradorIdAprobador() {
        return administradorIdAprobador;
    }

    public void setAdministradorIdAprobador(Usuario administradorIdAprobador) {
        this.administradorIdAprobador = administradorIdAprobador;
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
        if (!(object instanceof Prestamo)) {
            return false;
        }
        Prestamo other = (Prestamo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.unam.is20191.models.Prestamo[ id=" + id + " ]";
    }
    
}
