package mx.unam.is20191.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import mx.unam.is20191.dao.UsuarioDao;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

@ManagedBean
@SessionScoped
public class RegistroController {

    private final static String DOMINIO_CORREO = "@ciencias.unam.mx";

    private String userName, password, password2;

    private final UsuarioDao USUARIO_DAO;

    private UploadedFile file;

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

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

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public RegistroController() {
        USUARIO_DAO = new UsuarioDao();
    }

    public String loginUser() {
        /*try {
            Mail.mandarLinkDeRegistro("rodrigo.cardns@ciencias.unam.mx");
        } catch (MessagingException ex) {
            FacesContext.getCurrentInstance().addMessage("messages",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Por el momento no se pueden registrar nuevas cuentas. Inténtalo más tarde.", ""));
            System.err.println(ex);
            return null;
        }*/
        this.USUARIO_DAO.searchByUserNameOrEmail("user1");
        FacesContext.getCurrentInstance().addMessage("messages",
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "El usuario y/o la contraseña son inválidos.", ""));
        return null;
    }

    //Ejemplo para algunos casos que se necesitaran para validar.
    public void validatePassword(FacesContext context, UIComponent component, Object value) {
        // Retrieve the value passed to this method
        String confirmPassword = (String) value;
        // Retrieve the temporary value from the password field
        UIInput passwordInput = (UIInput) component.findComponent("password");
        String password = (String) passwordInput.getLocalValue();
        if (password == null || confirmPassword == null || !password.equals(confirmPassword)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Las contraseñas no coinciden", "Las contraseñas no coinciden");
            throw new ValidatorException(msg);
        }
    }

    /**
     * Método que valida si un usuario ya está registrado en la base de datos,
     * manda error si se escribe un usuario que ya esté registrado.
     *
     * @param context
     * @param component
     * @param value Es el valor obtenido del componente que llama al validador.
     */
    public void validateUniqueUserName(FacesContext context, UIComponent component, Object value) {
        if (USUARIO_DAO.userExist(value.toString())) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "El usuario ya existe, escriba otro.",
                    "El usuario ya existe, escriba otro.");
            throw new ValidatorException(msg);
        }
    }

    public void validateUniqueEmail(FacesContext context, UIComponent component, Object value) {
        if (USUARIO_DAO.mailExist(value + DOMINIO_CORREO)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "El correo que intenta dar ya está registrado, escriba otro.",
                    "El correo que intenta dar ya está registrado, escriba otro.");
            throw new ValidatorException(msg);
        }
    }

    public void uploadImg(FileUploadEvent e) throws FileNotFoundException {
        // Get uploaded file from the FileUploadEvent
        this.file = e.getFile();
        // Print out the information of the file
        System.out.println("Uploaded File Name Is :: " + file.getFileName() + " :: Uploaded File Size :: " + file.getSize());
    }

    public StreamedContent getImagestream() throws FileNotFoundException, Exception {
        if (file != null) {
            System.err.println("AAAAA");
            return new DefaultStreamedContent(file.getInputstream(), file.getContentType());
        } else {
            System.err.println("BBBBBBB");
            return new DefaultStreamedContent(new FileInputStream(new File("c:\\biosip-img\\profile\\default.png")), "png");
        }
    }

    public void clear() {
        System.err.println("duifhdiuk");
        this.file = null;
    }

}
