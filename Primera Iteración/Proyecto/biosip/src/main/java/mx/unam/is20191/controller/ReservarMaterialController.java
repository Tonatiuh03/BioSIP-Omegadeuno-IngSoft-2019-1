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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
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
public class ReservarMaterialController implements Serializable {

    private List<Material> listaPrestamo;
    private List<Material> listaPrestamoUnica;
    private boolean confirmarPrestamo;
    private boolean estado;
    private String nombreBtnAccion;
    private boolean exito;

    public ReservarMaterialController() {
        this.listaPrestamo = new ArrayList<Material>();
        this.listaPrestamoUnica = new ArrayList<Material>();
        this.confirmarPrestamo = false;
        this.nombreBtnAccion = "Confirmar Préstamo";
        this.exito = false;
    }

    public void nuevoPrestamo() {
        this.listaPrestamo = new ArrayList<Material>();
        this.listaPrestamoUnica = new ArrayList<Material>();
        this.confirmarPrestamo = false;
        this.nombreBtnAccion = "Confirmar Préstamo";
        this.exito = false;
        this.estado = false;
    }

    public List<Material> getListaPrestamo() {
        return listaPrestamo;
    }

    public void setListaPrestamo(List<Material> listaPrestamo) {
        this.listaPrestamo = listaPrestamo;
    }

    public List<Material> getListaPrestamoUnica() {
        return listaPrestamoUnica;
    }

    public void setListaPrestamoUnica(List<Material> listaPrestamoUnica) {
        this.listaPrestamoUnica = listaPrestamoUnica;
    }

    public List<Material> getMateriales() {
        MaterialDao matdao = new MaterialDao();
        return matdao.getMateriales();
    }

    public boolean isConfirmarPrestamo() {
        return confirmarPrestamo;
    }

    public void setConfirmarPrestamo(boolean confirmarPrestamo) {
        this.confirmarPrestamo = confirmarPrestamo;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public boolean isExito() {
        return exito;
    }

    public void setExito(boolean exito) {
        this.exito = exito;
    }

    public String getNombreBtnAccion() {
        if (this.estado) {
            this.nombreBtnAccion = "Continuar Agregando";
        } else {
            this.nombreBtnAccion = "Confirmar Préstamo";
        }
        return nombreBtnAccion;
    }

    public void setNombreBtnAccion(String nombreBtnAccion) {
        this.nombreBtnAccion = nombreBtnAccion;
    }

    public boolean habilitarBoton() {
        boolean habilitar = false;
        if (this.listaPrestamoUnica.isEmpty() && this.estado == true) {
            habilitar = false;
        } else if (!this.listaPrestamo.isEmpty() && this.estado == false) {
            habilitar = false;
        } else if (this.listaPrestamoUnica.isEmpty() && this.estado == false) {
            habilitar = true;
        }
        return habilitar;
    }

    public void generarPrestamo() throws Exception {
        this.estado = !this.estado;
    }

    public void agregar(Material m) {
        this.listaPrestamo.add(m);
        if (!this.listaPrestamoUnica.contains(m)) {
            this.listaPrestamoUnica.add(m);
        }
    }

    public void eliminar(Material m) throws Exception {
        this.listaPrestamo.remove(m);
        if (!this.listaPrestamo.contains(m)) {
            this.listaPrestamoUnica.remove(m);
        }
    }

    public int contarMateriales(Material m) throws Exception {
        int cont = 0;
        for (Material material : listaPrestamo) {
            if (material.equals(m)) {
                cont++;
            }
        }
        return cont;
    }

    public boolean hayDispobibles(Material m) throws Exception {
        if (m.getDisponibles() > this.contarMateriales(m)) {
            return true;
        } else {
            return false;
        }
    }

    public void crearPrestamo() throws Exception {
        UsuarioDao usuarioDao = new UsuarioDao();
        SessionController sc = new SessionController();
        Usuario usuario = usuarioDao.searchByUserNameOrEmail("dam");

        try {
            MaterialDao m = new MaterialDao();

            Prestamo prestamo = new Prestamo();
            prestamo.setUsuarioId(usuario);

            PrestamoDao presd = new PrestamoDao();
            presd.getEntityManager().getTransaction().begin();
            prestamo = presd.update(prestamo);
            presd.getEntityManager().getTransaction().commit();
            PrestamoMaterialDao presmatd = new PrestamoMaterialDao();;
            presmatd.getEntityManager().getTransaction().begin();

            PrestamoMaterial presmat = new PrestamoMaterial();

            int disponibles = 0;
            int materialesPrestados = 0;
            for (Material material : listaPrestamoUnica) {
                disponibles = material.getDisponibles();
                materialesPrestados = this.contarMateriales(material);
                material.setDisponibles(disponibles - materialesPrestados);
                m.getEntityManager().getTransaction().begin();
                material = m.update(material);
                m.getEntityManager().getTransaction().commit();
                presmat.setPrestamoMaterialPK(new PrestamoMaterialPK(prestamo.getId(), material.getId()));
                presmat.setElementosPrestados(materialesPrestados);
                presmatd.save(presmat);
            }
            presmatd.getEntityManager().getTransaction().commit();

            this.exito = true;

            FacesContext.getCurrentInstance().addMessage("nuevo_prestamo",
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Se ha generado un nuevo prestamo.",
                            "Se ha generado un nuevo prestamo."));

        } catch (IllegalArgumentException ex) {
            FacesContext.getCurrentInstance().addMessage("nuevo_prestamo",
                    new FacesMessage(FacesMessage.SEVERITY_FATAL,
                            "Por el momento no podemos generar un nuevo Prestamo, inténtelo más tarde.",
                            "Por el momento no podemos generar un nuevo Prestamo, inténtelo más tarde."));
        }
    }
}
