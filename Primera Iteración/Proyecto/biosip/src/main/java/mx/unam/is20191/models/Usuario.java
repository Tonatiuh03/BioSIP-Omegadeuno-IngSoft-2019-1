/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.unam.is20191.models;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import mx.unam.is20191.utils.Config;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author sds
 */
@Entity
@Table(catalog = "biosip", schema = "public", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"correo_ciencias"})
    , @UniqueConstraint(columnNames = {"user_name"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
    , @NamedQuery(name = "Usuario.findById", query = "SELECT u FROM Usuario u WHERE u.id = :id")
    , @NamedQuery(name = "Usuario.findByNombreCompleto", query = "SELECT u FROM Usuario u WHERE u.nombreCompleto = :nombreCompleto")
    , @NamedQuery(name = "Usuario.findByUserName", query = "SELECT u FROM Usuario u WHERE u.userName = :userName")
    , @NamedQuery(name = "Usuario.findByCorreoCiencias", query = "SELECT u FROM Usuario u WHERE u.correoCiencias = :correoCiencias")
    , @NamedQuery(name = "Usuario.findByPassword", query = "SELECT u FROM Usuario u WHERE u.password = :password")
    , @NamedQuery(name = "Usuario.findByFechaDeDesbloqueo", query = "SELECT u FROM Usuario u WHERE u.fechaDeDesbloqueo = :fechaDeDesbloqueo")
    , @NamedQuery(name = "Usuario.findByRutaImagen", query = "SELECT u FROM Usuario u WHERE u.rutaImagen = :rutaImagen")
    , @NamedQuery(name = "Usuario.findByValidado", query = "SELECT u FROM Usuario u WHERE u.validado = :validado")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Long id;
    @Basic(optional = false)
    @Column(name = "nombre_completo", nullable = false, length = 210)
    private String nombreCompleto;
    @Basic(optional = false)
    @Column(name = "user_name", nullable = false, length = 50)
    private String userName;
    @Basic(optional = false)
    @Column(name = "correo_ciencias", nullable = false, length = 150)
    private String correoCiencias;
    @Basic(optional = false)
    @Column(nullable = false, length = 100)
    private String password;
    @Column(name = "fecha_de_desbloqueo")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDeDesbloqueo;
    @Column(name = "ruta_imagen", length = 100)
    private String rutaImagen;
    @Basic(optional = false)
    @Column(nullable = false)
    private boolean validado;
    @ManyToMany(cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE
    })
    @JoinTable(name = "usuario_perfil", joinColumns = {
        @JoinColumn(name = "usuario_id", referencedColumnName = "id", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "perfil_id", referencedColumnName = "id", nullable = false)})
    private Set<Perfil> perfilSet;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "usuarioId", fetch = FetchType.LAZY)
    private Confirmacion confirmacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioId", fetch = FetchType.LAZY)
    private Set<Prestamo> prestamoSet;
    @OneToMany(mappedBy = "administradorIdAprobador", fetch = FetchType.LAZY)
    private Set<Prestamo> prestamoSet1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioIdAutor", fetch = FetchType.LAZY)
    private Set<Kit> kitSet;
    @OneToMany(mappedBy = "administradorIdAprobador", fetch = FetchType.LAZY)
    private Set<Kit> kitSet1;

    public Usuario() {
    }

    public Usuario(Long id) {
        this.id = id;
    }

    public Usuario(Long id, String nombreCompleto, String userName, String correoCiencias, String password, boolean validado) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
        this.userName = userName;
        this.correoCiencias = correoCiencias;
        this.password = password;
        this.validado = validado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCorreoCiencias() {
        return correoCiencias;
    }

    public void setCorreoCiencias(String correoCiencias) {
        this.correoCiencias = correoCiencias;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getFechaDeDesbloqueo() {
        return fechaDeDesbloqueo;
    }

    public void setFechaDeDesbloqueo(Date fechaDeDesbloqueo) {
        this.fechaDeDesbloqueo = fechaDeDesbloqueo;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    public boolean getValidado() {
        return validado;
    }

    public void setValidado(boolean validado) {
        this.validado = validado;
    }

    @XmlTransient
    public Set<Perfil> getPerfilSet() {
        return perfilSet;
    }

    public void setPerfilSet(Set<Perfil> perfilSet) {
        this.perfilSet = perfilSet;
    }

    public Confirmacion getConfirmacion() {
        return confirmacion;
    }

    public void setConfirmacion(Confirmacion confirmacion) {
        this.confirmacion = confirmacion;
    }

    @XmlTransient
    public Set<Prestamo> getPrestamoSet() {
        return prestamoSet;
    }

    public void setPrestamoSet(Set<Prestamo> prestamoSet) {
        this.prestamoSet = prestamoSet;
    }

    @XmlTransient
    public Set<Prestamo> getPrestamoSet1() {
        return prestamoSet1;
    }

    public void setPrestamoSet1(Set<Prestamo> prestamoSet1) {
        this.prestamoSet1 = prestamoSet1;
    }

    @XmlTransient
    public Set<Kit> getKitSet() {
        return kitSet;
    }

    public void setKitSet(Set<Kit> kitSet) {
        this.kitSet = kitSet;
    }

    @XmlTransient
    public Set<Kit> getKitSet1() {
        return kitSet1;
    }

    public void setKitSet1(Set<Kit> kitSet1) {
        this.kitSet1 = kitSet1;
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
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.unam.is20191.models.Usuario[ id=" + id + " ]";
    }

    public StreamedContent getImage() {
        try {
            return new DefaultStreamedContent(new FileInputStream(new File(Config.IMG_PROFILE_REPO + this.rutaImagen)), "png");
        } catch (IOException ex) {
            return null;
        }
    }

}
