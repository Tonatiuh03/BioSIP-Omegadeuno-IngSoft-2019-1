/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.unam.is20191.controller;

/**
 *
 * @author dams_
 */
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import mx.unam.is20191.dao.MaterialDao;
import mx.unam.is20191.dao.PrestamoDao;
import mx.unam.is20191.dao.PrestamoMaterialDao;
import mx.unam.is20191.dao.UsuarioDao;
import mx.unam.is20191.models.Material;
import mx.unam.is20191.models.Prestamo;
import mx.unam.is20191.models.PrestamoMaterial;
import mx.unam.is20191.models.PrestamoMaterialPK;
import mx.unam.is20191.models.Usuario;

@ManagedBean
@SessionScoped
public class ConfirmarPrestamoController implements Serializable {

    private List<Prestamo> listaPrestamos;
    private List<Material> listaMateriales;
    private String usuario;
    private Date fechaSolicitud;
    private Date fechaAprobacion;
    private Date fechaDevolucion;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public Date getFechaAprobacion() {
        return fechaAprobacion;
    }

    public void setFechaAprobacion(Date fechaAprobacion) {
        this.fechaAprobacion = fechaAprobacion;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public List<Prestamo> getListaPrestamos() {
        PrestamoDao prestamoDao = new PrestamoDao();
        return prestamoDao.getPrestamos();
    }

    public void setListaPrestamos(List<Prestamo> listaPrestamos) {
        this.listaPrestamos = listaPrestamos;
    }

    public List<Material> getListaMateriales(Prestamo prestamo) {
        PrestamoMaterialDao pmd = new PrestamoMaterialDao();
        return pmd.getMateriales(prestamo);
    }

    public void setListaMateriales(List<Material> listaMateriales) {
        this.listaMateriales = listaMateriales;
    }

    public void confirmarMaterialPrestado() {
        Usuario usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");

        if (true) {
            FacesContext.getCurrentInstance().addMessage("mensaje-prestado",
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Se ha entregado el material al usuario: " + usuario.getUserName() + ".",
                            "Se ha entregado el material al usuario: " + usuario.getUserName() + "."));
        } else if (false) {
            FacesContext.getCurrentInstance().addMessage("mensaje-prestado",
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Lo sentimos, El periodo de reserva ha expirado.",
                            "El material ya no se encuentra reservado."));
        } else {
            FacesContext.getCurrentInstance().addMessage("mensaje-prestado",
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Lo sentimos, por el momento no se puede realizar la acción",
                            "Lo sentimos, por el momento no se puede realizar la acción"));
        }
    }

    public void confirmarMaterialDevuelto() {
        if (true) {
            FacesContext.getCurrentInstance().addMessage("mensaje-devuelto",
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "El materil prestado ha sido devuelto al inventario.",
                            "El materil prestado ha sido devuelto al inventario."));
        }
    }

    public void mostrarDetallePrestamo(Prestamo p) {
        for(Material m: this.getListaMateriales(p)){
            System.out.println(m.getNombre());
        }
    }

}
