/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.unam.is20191.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import mx.unam.is20191.models.Usuario;
import mx.unam.is20191.utils.Config;
import static mx.unam.is20191.utils.Config.IMG_PROFILE_REPO;

/**
 *
 * @author sds
 */
@ManagedBean
@SessionScoped
public class SessionController {

    public String getImageUrl() {
        return IMG_PROFILE_REPO + ((Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user")).getRutaImagen();
    }
}
