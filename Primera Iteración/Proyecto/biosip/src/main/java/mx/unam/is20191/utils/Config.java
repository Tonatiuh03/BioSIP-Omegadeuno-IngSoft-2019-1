/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.unam.is20191.utils;

/**
 *
 * @author jrcvd
 */
public class Config {

    public final static String DOMINIO_CORREO = "@ciencias.unam.mx",
            IMG_REPO = "c:\\biosip-img\\",
            IMG_PROFILE_REPO = IMG_REPO + "profile\\",
            IMG_MATERIAL_REPO = IMG_REPO + "material\\",
            IMG_PROFILE_REPO_DEFAULT_FILE_NAME = "default.png",
            IMG_MATERIAL_REPO_DEFAULT_FILE_NAME = "carga.png",
            IMG_PROFILE_REPO_DEFAULT = IMG_PROFILE_REPO + IMG_PROFILE_REPO_DEFAULT_FILE_NAME,
            IMG_MATERIAL_REPO_DEFAULT = IMG_MATERIAL_REPO + IMG_MATERIAL_REPO_DEFAULT_FILE_NAME,
            DOMINIO = "http://localhost:8084/",
            VISTA_CORREO = "views/pub/confirmacion_correo.xhtml?idc=",
            LOGIN_PAGE = "/index.xhtml",
            REGISTRO_PAGE = "/views/pub/registro.xhtml",
            USR_PRINCIPAL_PAGE = "/views/usr/reservarmaterial.xhtml",
            PROF_PRINCIPAL_PAGE = "/views/usr/reservarmaterial.xhtml",
            ADM_PRINCIPAL_PAGE = "/views/adm/confirmarprestamo.xhtml",
            URL_CORREO = DOMINIO + VISTA_CORREO;

}
