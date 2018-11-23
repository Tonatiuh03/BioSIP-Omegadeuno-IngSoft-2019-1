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
public class ConfirmarPrestamoController implements Serializable {

    private List<Prestamo> listaPrestamos;
    private List<Material> listaMateriales;
    private Date fechaSolicitud;
    private Date fechaAprobacion;
    private Date fechaDevolucion;
    private List<String> detalle;
    private Usuario usuario;
    private Prestamo prestamo;
    private boolean btnFechaAprobacion;
    private boolean btnFechaDevolucion;

    public ConfirmarPrestamoController() {
        this.usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
    }

    public boolean isBtnFechaAprobacion() {
        return btnFechaAprobacion;
    }

    public void setBtnFechaAprobacion(boolean btnFechaAprobacion) {
        this.btnFechaAprobacion = btnFechaAprobacion;
    }

    public boolean isBtnFechaDevolucion() {
        return btnFechaDevolucion;
    }

    public void setBtnFechaDevolucion(boolean btnFechaDevolucion) {
        this.btnFechaDevolucion = btnFechaDevolucion;
    }

    public Prestamo getPrestamo() {
        return prestamo;
    }

    public Prestamo cargarPrestamo(AjaxBehaviorEvent event) {
        this.prestamo = (Prestamo) event.getComponent().getAttributes().get("prestamo");
        return prestamo;
    }

    public void setPrestamo(Prestamo prestamo) {
        this.prestamo = prestamo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<String> getDetalle() {
        return detalle;
    }

    public void setDetalle(List<String> detalle) {
        this.detalle = detalle;
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

    public List<Pair<Material, Integer>> getListaMateriales(Prestamo prestamo) {
        PrestamoMaterialDao pmd = new PrestamoMaterialDao();
        return pmd.getMateriales(prestamo);
    }

    public void setListaMateriales(List<Material> listaMateriales) {
        this.listaMateriales = listaMateriales;
    }

    public void confirmarMaterialPrestado(ActionEvent event) throws Exception {
        try {
            Prestamo p = (Prestamo) event.getComponent().getAttributes().get("prestamo");
            Date date = new Date();
            if (!(this.diferenciaDeFechas(p.getFechaDeSolicitud(), new Date()) > 3)) {
                PrestamoDao presd = new PrestamoDao();
                presd.getEntityManager().getTransaction().begin();
                p = presd.getByKey(p.getId());
                p.setFechaDeAprobacion(date);
                p.setAdministradorIdAprobador(usuario);
                System.err.println("Prestamo :" + p.getId());
                presd.update(p);
                presd.getEntityManager().getTransaction().commit();
                FacesContext.getCurrentInstance().addMessage("mensajes",
                        new FacesMessage(FacesMessage.SEVERITY_INFO,
                                "Se ha prestado material al usuario \"" + p.getUsuarioId().getNombreCompleto() + "\".",
                                "Préstamo " + p.getId() + " iniciado."));
            } else if (this.diferenciaDeFechas(p.getFechaDeSolicitud(), new Date()) > 3 && p.getFechaDeAprobacion() == null && p.getFechaDeDevolucion() == null) {
                PrestamoDao presd = new PrestamoDao();
                presd.getEntityManager().getTransaction().begin();
                p = presd.getByKey(p.getId());
                //Actualizamos las dos fechas para saber que el prestamo se cierra en automatico.
                p.setFechaDeAprobacion(date);
                p.setFechaDeDevolucion(date);
                p.setAdministradorIdAprobador(usuario);
                System.err.println("Prestamo :" + p.getId());

                MaterialDao materialDao = new MaterialDao();
                Material material = new Material();
                for (Pair<Material, Integer> par : this.getListaMateriales(p)) {
                    materialDao.getEntityManager().getTransaction().begin();
                    material = materialDao.getByKey(par.getKey().getId());
                    material.setDisponibles(material.getDisponibles() + par.getValue());
                    materialDao.update(material);
                    materialDao.getEntityManager().getTransaction().commit();
                }
                presd.update(p);

                presd.getEntityManager().getTransaction().commit();
                FacesContext.getCurrentInstance().addMessage("mensajes",
                        new FacesMessage(FacesMessage.SEVERITY_INFO,
                                "Lo sentimos, el periodo de reserva ha terminado para el prestamo con No.: " + p.getId(),
                                "El material ya no se encuentra apartado para el usuario \"" + p.getUsuarioId().getNombreCompleto() + "\"."));
            } else {
                FacesContext.getCurrentInstance().addMessage("mensajes",
                        new FacesMessage(FacesMessage.SEVERITY_INFO,
                                "Lo sentimos, el periodo de reserva ha terminado para el prestamo con No.: " + p.getId(),
                                "El material ya no se encuentra apartado para el usuario \"" + p.getUsuarioId().getNombreCompleto() + "\"."));
            }

        } catch (IllegalArgumentException ex) {
            FacesContext.getCurrentInstance().addMessage("mensajes",
                    new FacesMessage(FacesMessage.SEVERITY_FATAL,
                            "Por el momento no podemos generar la confirmación del Prestamo, inténtelo más tarde.",
                            "Por el momento no podemos generar la confirmación del Prestamo, inténtelo más tarde."));
        }
    }

    public void confirmarMaterialDevuelto(ActionEvent event) {
        try {
            Prestamo p = (Prestamo) event.getComponent().getAttributes().get("prestamo");
            Date date = new Date();

            PrestamoDao presd = new PrestamoDao();
            presd.getEntityManager().getTransaction().begin();
            p = presd.getByKey(p.getId());
            p.setFechaDeDevolucion(date);
            System.err.println("Prestamo :" + p.getId());

            MaterialDao materialDao = new MaterialDao();
            Material material = new Material();
            for (Pair<Material, Integer> par : this.getListaMateriales(p)) {
                materialDao.getEntityManager().getTransaction().begin();
                material = materialDao.getByKey(par.getKey().getId());
                material.setDisponibles(material.getDisponibles() + par.getValue());
                materialDao.update(material);
                materialDao.getEntityManager().getTransaction().commit();
            }

            presd.update(p);
            presd.getEntityManager().getTransaction().commit();

            FacesContext.getCurrentInstance().addMessage("mensajes",
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "El material prestado al usuario \"" + p.getUsuarioId().getNombreCompleto() + "\", ha sido devuelto al inventario.",
                            "Préstamo " + p.getId() + " finalizado."));

        } catch (IllegalArgumentException ex) {
            FacesContext.getCurrentInstance().addMessage("mensajes",
                    new FacesMessage(FacesMessage.SEVERITY_FATAL,
                            "Por el momento no podemos generar la confirmación del Prestamo, inténtelo más tarde.",
                            "Por el momento no podemos generar la confirmación del Prestamo, inténtelo más tarde."));
        }
    }

    public List mostrarDetallePrestamo(Prestamo p) throws Exception {
        this.detalle = new ArrayList<String>();
        ArrayList<String> lista = new ArrayList<>();
        System.err.println(p.toString());
        for (Pair<Material, Integer> m : this.getListaMateriales(p)) {
            this.detalle.add("Material: " + m.getKey().getNombre() + "\t - Cantidad: " + m.getValue().toString());
            lista.add("Material: " + m.getKey().getNombre() + "\t - Cantidad: " + m.getValue().toString());
            System.err.println("Material: " + m.getKey().getNombre() + "\t - Cantidad: " + m.getValue().toString());
        }
        return lista;
    }

    public boolean habilitarBtnMaterialPrestado(Prestamo p) {
        boolean deshabilitar = false;
        if (p.getFechaDeAprobacion() != null) {
            deshabilitar = true;
        } else if (p.getFechaDeAprobacion() == null && p.getFechaDeDevolucion() == null) {
            deshabilitar = false;
        } else if (p.getFechaDeAprobacion() == p.getFechaDeDevolucion()) {
            deshabilitar = true;
        }

        return deshabilitar;
    }

    public boolean habilitarBtnMaterialDevuelto(Prestamo p) {
        boolean deshabilitar = false;
        if (p.getFechaDeAprobacion() == null || p.getFechaDeAprobacion() == p.getFechaDeDevolucion()) {
            return true;
        }
        if (this.diferenciaDeFechas(p.getFechaDeSolicitud(), new Date()) > 3 || p.getFechaDeDevolucion() != null) {
            deshabilitar = true;
        }

        return deshabilitar;
    }

    public String estadoPrestamo(Prestamo p) {
        String estado = "Desconocido";
        if (p.getFechaDeAprobacion() != null) {
            estado = "Iniciado";
            if (this.diferenciaDeFechas(p.getFechaDeSolicitud(), p.getFechaDeAprobacion()) > 3  && p.getFechaDeDevolucion() != null) {
                estado = "Apartado Expirado - Materiales disponibles en inventario";
            }else if (p.getFechaDeDevolucion() != null) {
                estado = "Finalizado - Materiales disponibles en inventario";
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

    /*
    * Función que dado un Préstamo, con base en su fecha de solicitud y la fecha actual
    * obtiene la diferencia de días entre ambas fechas.
    * @param p - Un objeto de tipo Prestamo
    * @return limite - el número de días entre ambas fechas.
     */
    public Long diferenciaDeFechas(Date uno, Date dos) {
        Long diferencia = dos.getTime() - uno.getTime();
        Long limite = TimeUnit.DAYS.convert(diferencia, TimeUnit.MILLISECONDS);
        return limite;
    }

}
