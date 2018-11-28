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
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import javafx.util.Pair;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
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
public class MisReservaciones extends ConfirmarPrestamoController implements Serializable {

    private ArrayList<Prestamo> reservaciones;
    private Usuario usuario;

    public MisReservaciones() {
        this.usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        System.out.println("Usuario " + this.usuario.getNombreCompleto());
    }

    public List<Prestamo> getReservaciones() {
        PrestamoDao prestamodao = new PrestamoDao();
        return prestamodao.getPrestamos(usuario);
    }

    public void setReservaciones(ArrayList<Prestamo> reservaciones) {
        this.reservaciones = reservaciones;
    }

    public void eliminarPrestamo(ActionEvent event) throws Exception {
        try {
            Prestamo prestamo = (Prestamo) event.getComponent().getAttributes().get("prestamo");
            Date date = new Date();
            if (prestamo.getFechaDeAprobacion() == null) {
                PrestamoDao presd = new PrestamoDao();
                presd.getEntityManager().getTransaction().begin();
                prestamo = presd.getByKey(prestamo.getId());

                MaterialDao materialDao = new MaterialDao();
                Material material = new Material();
                for (Pair<Material, Integer> par : this.getListaMateriales(prestamo)) {
                    materialDao.getEntityManager().getTransaction().begin();
                    material = materialDao.getByKey(par.getKey().getId());
                    material.setDisponibles(material.getDisponibles() + par.getValue());
                    materialDao.update(material);
                    materialDao.getEntityManager().getTransaction().commit();
                }

                presd.delete(prestamo);
                presd.getEntityManager().getTransaction().commit();

                FacesContext.getCurrentInstance().addMessage("mensajes",
                        new FacesMessage(FacesMessage.SEVERITY_INFO,
                                "Se ha eliminado tu préstamo con número: " + prestamo.getId() + ".",
                                "Préstamo " + prestamo.getId() + " cancelado."));
            } else {
                FacesContext.getCurrentInstance().addMessage("mensajes",
                        new FacesMessage(FacesMessage.SEVERITY_INFO,
                                "El préstamo con número: " + prestamo.getId() + ", ya ha sido aprovado.",
                                "El préstamo " + prestamo.getId() + " no se puede cancelar."));
            }
        } catch (IllegalArgumentException ex) {
            FacesContext.getCurrentInstance().addMessage("mensajes",
                    new FacesMessage(FacesMessage.SEVERITY_FATAL,
                            "Por el momento no podemos modificar los prestamos, inténtelo más tarde.",
                            "Por el momento no podemos modificar los prestamos, inténtelo más tarde."));
        }

    }

    public boolean posibleCancelacion(Prestamo prestamo) {
        return prestamo.getFechaDeAprobacion() == null;
    }

    public String estadoPrestamo(Prestamo p) {
        String estado = "Desconocido";
        if (p.getFechaDeAprobacion() != null) {
            estado = "Aprobado actualmente";
            if (this.diferenciaDeFechas(p.getFechaDeSolicitud(), p.getFechaDeAprobacion()) > 3 && p.getFechaDeDevolucion() != null) {
                estado = "Apartado Expirado";
            } else if (p.getFechaDeDevolucion() != null) {
                estado = "Finalizado";
            }
        } else {
            if (this.diferenciaDeFechas(p.getFechaDeSolicitud(), new Date()) > 3 && p.getFechaDeAprobacion() == null) {
                estado = "Apartado Expirado";
            } else if (p.getFechaDeAprobacion() == null && p.getFechaDeDevolucion() == null) {
                estado = "Reservado";
            }
        }
        return estado;
    }

}
