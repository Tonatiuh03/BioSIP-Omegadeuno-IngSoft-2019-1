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
import mx.unam.is20191.models.Usuario;

@ManagedBean
@SessionScoped
public class ReservarMaterialController implements Serializable{

    private List<Material> listaPrestamo;
    private List<Material> listaPrestamoUnica;
    private boolean confirmarPrestamo;
    private boolean estado;

    public ReservarMaterialController() {
        this.listaPrestamo = new ArrayList<Material>();
        this.listaPrestamoUnica = new ArrayList<Material>();
        this.confirmarPrestamo = false;
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
        Date date = new Date();

        try {
            MaterialDao m = new MaterialDao();

            Prestamo prestamo = new Prestamo();
            prestamo.setUsuarioId(usuario);
            prestamo.setFechaDeSolicitud(date);
            //prestamo.setAdministradorIdAprobador(usuario);
            //prestamo.setFechaDeAprobacion(date);
            //prestamo.setFechaDeDevolucion(date);
            prestamo.setId(6L); //Aquí hay que hacer que se genere en automático el id para el siguiente prestamo.
            
            PrestamoDao presd = new PrestamoDao();
            presd.getEntityManager().getTransaction().begin();
            prestamo = presd.update(prestamo);

            PrestamoMaterialDao presmatd = new PrestamoMaterialDao();;
            presmatd.getEntityManager().getTransaction().begin();

            PrestamoMaterial presmat = new PrestamoMaterial();
            
            int disponibles = 0;
            int materialesPrestados = 0;
            for (Material material : listaPrestamoUnica) {

                disponibles = material.getDisponibles();
                materialesPrestados = this.contarMateriales(material);
                material.setDisponibles(disponibles - materialesPrestados);
                //m.update(material); //Aun falta actualizar la cantidad de los materiales disponibles.

                //Hace falta agregar los registros en la tabla de Prestamo_Material
                //presmat.setPrestamo(prestamo);
                //presmat.setMaterial(material);
                //presmat.setElementosPrestados(materialesPrestados);

                //presmatd.save(presmat);
            }
            presmatd.getEntityManager().getTransaction().commit();
            presd.getEntityManager().getTransaction().commit();
            
            FacesContext.getCurrentInstance().addMessage("nuevo_prestamo",
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Se ha generado un nuevo prestamo.",
                            "Se ha generado un nuevo prestamo."));
            this.setEstado(false);
        } catch (IllegalArgumentException ex) {
            FacesContext.getCurrentInstance().addMessage("nuevo_prestamo",
                    new FacesMessage(FacesMessage.SEVERITY_FATAL,
                            "Por el momento no podemos generar un nuevo Prestamo, inténtelo más tarde.",
                            "Por el momento no podemos generar un nuevo Prestamo, inténtelo más tarde."));
        }
    }
}
