/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.unam.is20191.models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sds
 */
@Entity
@Table(catalog = "biosip", schema = "public", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"usuario_id"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Confirmacion.findAll", query = "SELECT c FROM Confirmacion c")
    , @NamedQuery(name = "Confirmacion.findByToken", query = "SELECT c FROM Confirmacion c WHERE c.token = :token")
    , @NamedQuery(name = "Confirmacion.findByFechaDeAlta", query = "SELECT c FROM Confirmacion c WHERE c.fechaDeAlta = :fechaDeAlta")})
public class Confirmacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(nullable = false, length = 100)
    private String token;
    @Basic(optional = false)
    @Column(name = "fecha_de_alta",insertable=false, nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDeAlta;
    @JoinColumn(name = "usuario_id", referencedColumnName = "id", nullable = false)
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario usuarioId;

    public Confirmacion() {
    }

    public Confirmacion(String token) {
        this.token = token;
    }

    public Confirmacion(String token, Date fechaDeAlta) {
        this.token = token;
        this.fechaDeAlta = fechaDeAlta;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getFechaDeAlta() {
        return fechaDeAlta;
    }

    public void setFechaDeAlta(Date fechaDeAlta) {
        this.fechaDeAlta = fechaDeAlta;
    }

    public Usuario getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Usuario usuarioId) {
        this.usuarioId = usuarioId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (token != null ? token.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Confirmacion)) {
            return false;
        }
        Confirmacion other = (Confirmacion) object;
        if ((this.token == null && other.token != null) || (this.token != null && !this.token.equals(other.token))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.unam.is20191.models.Confirmacion[ token=" + token + " ]";
    }
    
}
