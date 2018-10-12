package mx.unam.is20191.controller;

import java.util.Locale;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.mail.MessagingException;
import mx.unam.is20191.dao.UsuarioDao;
import mx.unam.is20191.utils.Mail;

@ManagedBean
@RequestScoped
public class RegistroController {

    private String userName, password;

    private final UsuarioDao USUARIO_DAO;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RegistroController() {
        FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale("es-Mx"));
        USUARIO_DAO = new UsuarioDao();
    }

    public String loginUser() {
        try {
            Mail.mandarLinkDeRegistro("rodrigo.cardns@ciencias.unam.mx");
        } catch (MessagingException ex) {
            FacesContext.getCurrentInstance().addMessage("messages",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Por el momento no se pueden registrar nuevas cuentas. Inténtalo más tarde.", ""));
            System.err.println(ex);
            return null;
        }
        this.USUARIO_DAO.searchByUserNameOrEmail("user1");
        FacesContext.getCurrentInstance().addMessage("messages",
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "El usuario y/o la contraseña son inválidos.", ""));
        return null;
    }

}
