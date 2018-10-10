package mx.unam.is20191.controller;

import java.util.Locale;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import mx.unam.is20191.dao.UsuarioDao;

@ManagedBean
@RequestScoped
public class LoginController {

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

    public LoginController() {
        FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale("es-Mx"));
        USUARIO_DAO = new UsuarioDao();
    }

    public String loginUser() {
        this.USUARIO_DAO.searchByUserNameOrEmail("user1");
        FacesContext.getCurrentInstance().addMessage("messages",
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "El usuario y/o la contraseña son inválidos.", ""));
        return null;
    }

}
