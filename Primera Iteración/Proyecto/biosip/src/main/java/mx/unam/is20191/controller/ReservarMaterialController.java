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
import mx.unam.is20191.dao.CategoriaDao;
import mx.unam.is20191.dao.MaterialDao;
import mx.unam.is20191.dao.PrestamoDao;
import mx.unam.is20191.dao.PrestamoMaterialDao;
import mx.unam.is20191.dao.SubcategoriaDao;
import mx.unam.is20191.dao.UsuarioDao;
import mx.unam.is20191.models.Categoria;
import mx.unam.is20191.models.Material;
import mx.unam.is20191.models.Prestamo;
import mx.unam.is20191.models.PrestamoMaterial;
import mx.unam.is20191.models.PrestamoMaterialPK;
import mx.unam.is20191.models.Subcategoria;
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
    private int cantidad;
    private HashMap<Long, Integer> carritoCantidades;

    private String nombreMaterial;
    private Categoria categoria;
    private Subcategoria subcategoria;
    

    public ReservarMaterialController() {
        this.listaPrestamo = new ArrayList<Material>();
        this.listaPrestamoUnica = new ArrayList<Material>();
        this.confirmarPrestamo = false;
        this.nombreBtnAccion = "Confirmar Préstamo";
        this.exito = false;
        this.cantidad = 1;
        this.carritoCantidades = new HashMap<>();
        this.nombreMaterial = "";
    }

    public void nuevoPrestamo() {
        this.listaPrestamo = new ArrayList<Material>();
        this.listaPrestamoUnica = new ArrayList<Material>();
        this.confirmarPrestamo = false;
        this.nombreBtnAccion = "Confirmar Préstamo";
        this.exito = false;
        this.estado = false;
        this.cantidad = 1;
        this.carritoCantidades = new HashMap<>();
    }

    public List<Categoria> getCategorias() {
        return new CategoriaDao().getCategorias();
    }

    public List<Subcategoria> getSubcategorias() {
        return new SubcategoriaDao().getSubcategorias();
    }

    
    public String getNombreMaterial() {
        return nombreMaterial;
    }

    public void setNombreMaterial(String nombreMaterial) {
        this.nombreMaterial = nombreMaterial;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Subcategoria getSubcategoria() {
        return subcategoria;
    }

    public void setSubcategoria(Subcategoria subcategoria) {
        this.subcategoria = subcategoria;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
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
        return matdao.searchMaterial(this.nombreMaterial,
                this.categoria == null ? -1 : this.categoria.getId(),
                this.subcategoria == null ? -1 : this.subcategoria.getId());
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
            this.nombreBtnAccion = "Continúa Agregando";
        } else {
            this.nombreBtnAccion = "Confirma Préstamo";
        }
        return nombreBtnAccion;
    }

    public void setNombreBtnAccion(String nombreBtnAccion) {
        this.nombreBtnAccion = nombreBtnAccion;
    }

    public boolean habilitarBoton() {
        boolean deshabilitar = false;
        if (this.listaPrestamoUnica.isEmpty() && this.estado == true) {
            deshabilitar = false;
        } else if (!this.listaPrestamo.isEmpty() && this.estado == false) {
            deshabilitar = false;
        } else if (this.listaPrestamoUnica.isEmpty() && this.estado == false) {
            deshabilitar = true;
        }
        return deshabilitar;
    }

    public void avisarPrestamoVacio() {
        if (this.listaPrestamoUnica.isEmpty() && this.estado == true) {
            FacesContext.getCurrentInstance().addMessage("prestamo_vacio",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "No hay elementos para generar un Préstamo.",
                            "Por favor haz clic en 'Continúa Agregando'."));
        }
    }

    public void generarPrestamo() throws Exception {
        this.estado = !this.estado;
    }

    public void changeMaterialCantidad(Material m) {
        System.err.println(m.getId() + "#" + this.cantidad);
        this.carritoCantidades.put(m.getId(), this.cantidad);
    }

    public void agregar(ActionEvent event) throws Exception {
        this.cantidad = 1;
        Material m = (Material) event.getComponent().getAttributes().get("material");
        Integer n = this.carritoCantidades.get(m.getId());
        if (n == null) {
            n = 0;
        }
        if ((n + this.contarMateriales(m)) <= m.getDisponibles() && n > 0) {
            for (int i = 0; i < n; i++) {
                this.listaPrestamo.add(m);
            }
            if (!this.listaPrestamoUnica.contains(m)) {
                this.listaPrestamoUnica.add(m);
            }
            FacesContext.getCurrentInstance().addMessage("mensaje-agregar-material",
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Se ha agregado una unidad del material: -" + m.getNombre() + "- a la lista del préstamo.",
                            "Hay " + this.contarMateriales(m) + " unidades del material en la lista del préstamo."));
        } else {
            FacesContext.getCurrentInstance().addMessage("mensaje-agregar-material",
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Lo sentimos, la cantidad solicitada del material no se encuentra disponible.",
                            "Por favor revise la cantidad de material disponible."));
        }
    }

    public void eliminar(Material m) throws Exception {
        this.listaPrestamo.remove(m);
        if (!this.listaPrestamo.contains(m)) {
            this.listaPrestamoUnica.remove(m);
        }
        FacesContext.getCurrentInstance().addMessage("mensaje-eliminar-material",
                new FacesMessage(FacesMessage.SEVERITY_WARN,
                        "Se ha quitado una unidad del material: -" + m.getNombre() + "- de la lista del préstamo.",
                        "Hay " + this.contarMateriales(m) + " unidades del material en la lista del préstamo."));
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

        Usuario usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        LocalDateTime ldt = LocalDateTime.now();
        String fechaLimite = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.getDefault()).format(ldt);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Calendar c = Calendar.getInstance();
        c.setTime(sdf.parse(fechaLimite));
        c.add(Calendar.DATE, 3);  // agregamos 3 días a la fecha actual
        fechaLimite = sdf.format(c.getTime());  // obtenemos la fecha limite del préstamo

        try {
            MaterialDao materialdao = new MaterialDao();

            Prestamo prestamo = new Prestamo();
            prestamo.setUsuarioId(usuario);

            PrestamoDao presd = new PrestamoDao();
            presd.getEntityManager().getTransaction().begin();
            prestamo = presd.update(prestamo);
            presd.getEntityManager().getTransaction().commit();
            PrestamoMaterialDao presmatd = new PrestamoMaterialDao();;

            int disponibles = 0;
            int materialesPrestados = 0;
            for (Material material : listaPrestamoUnica) {

                disponibles = material.getDisponibles();
                materialesPrestados = this.contarMateriales(material);

                material.setDisponibles(disponibles - materialesPrestados);
                materialdao.getEntityManager().getTransaction().begin();
                material = materialdao.update(material);
                materialdao.getEntityManager().getTransaction().commit();

                PrestamoMaterial presmat = new PrestamoMaterial();
                presmatd.getEntityManager().getTransaction().begin();
                presmat.setPrestamoMaterialPK(new PrestamoMaterialPK(prestamo.getId(), material.getId()));
                presmat.setElementosPrestados(materialesPrestados);
                presmatd.save(presmat);
                presmatd.getEntityManager().getTransaction().commit();
            }

            this.exito = true;

            FacesContext.getCurrentInstance().addMessage("nuevo_prestamo",
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Se ha generado un nuevo préstamo, No. de Préstamo: " + prestamo.getId() + ".",
                            "Tiene hasta el día " + fechaLimite + " para recoger los materiales."));

        } catch (IllegalArgumentException ex) {
            FacesContext.getCurrentInstance().addMessage("nuevo_prestamo",
                    new FacesMessage(FacesMessage.SEVERITY_FATAL,
                            "Por el momento no podemos generar un nuevo Prestamo, inténtelo más tarde.",
                            "Por el momento no podemos generar un nuevo Prestamo, inténtelo más tarde."));
        }
    }
}
