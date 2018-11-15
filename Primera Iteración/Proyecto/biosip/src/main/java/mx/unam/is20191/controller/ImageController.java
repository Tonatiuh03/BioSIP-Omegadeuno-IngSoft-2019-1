/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.unam.is20191.controller;

import java.io.Serializable;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import mx.unam.is20191.dao.MaterialDao;
import mx.unam.is20191.dao.UsuarioDao;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 * Controlador que maneja operaciones de imágenes con algunos objetos.
 *
 * @author Josué Rodrigo Cárdenas Vallarta
 */
@ManagedBean
@ApplicationScoped
public class ImageController implements Serializable {

    /**
     * Método que obtiene el stream de la imagen del usuario a partir de un id
     * de usuario.
     *
     * @return El stream de la imagen del usuario seleccionado.
     */
    public StreamedContent getListUserImage() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            return new DefaultStreamedContent();
        } else {
            String id = context.getExternalContext().getRequestParameterMap().get("id");
            return new UsuarioDao().getByKey(Long.valueOf(id)).getImage();
        }

    }

    /**
     * Método que obtiene el stream de la imagen de un material a partir de un
     * id de material.
     *
     * @return El stream de la imagen del material seleccionado.
     */
    public StreamedContent getListMaterialImage() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            return new DefaultStreamedContent();
        } else {
            String id = context.getExternalContext().getRequestParameterMap().get("id");
            return new MaterialDao().getByKey(Long.valueOf(id)).getImage();
        }

    }
}
