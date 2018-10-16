/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.unam.is20191.utils;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author sds
 */
public class Mail {

    private final static String EMAIL = "biosip.ciencias@gmail.com", PASSWORD = "SDv3We34gre5is$fdLk5erdGwq&#",
            MAIL_DE_REGISTRO = "Estimado %s:"
            + "<br>Dale click al siguiente enlace para concluir tu registro<br>"
            + "%s<br><br>"
            + "Saludos por parte del equipo de BioSIP";

    private final static Properties MAIL_SERVER_PROPERTIES;
    private static Session getMailSession;
    private static MimeMessage generateMailMessage;

    static {
        // Step1
        System.out.println("\n 1st ===> setup Mail Server Properties..");
        MAIL_SERVER_PROPERTIES = System.getProperties();
        MAIL_SERVER_PROPERTIES.put("mail.smtp.port", "587");
        MAIL_SERVER_PROPERTIES.put("mail.smtp.auth", "true");
        MAIL_SERVER_PROPERTIES.put("mail.smtp.starttls.enable", "true");
        MAIL_SERVER_PROPERTIES.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        System.out.println("Mail Server Properties have been setup successfully..");
    }

    public static void mandarLinkDeRegistro(String mailDestinatario, String nombreCompletoUsuario, String token) throws AddressException, MessagingException {
        // Step2
        System.out.println("\n\n 2nd ===> get Mail Session..");
        getMailSession = Session.getDefaultInstance(MAIL_SERVER_PROPERTIES, null);
        generateMailMessage = new MimeMessage(getMailSession);
        generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(mailDestinatario));
        //generateMailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress(""));
        generateMailMessage.setSubject("Registro a BioSIP");
        generateMailMessage.setContent(String.format(MAIL_DE_REGISTRO, nombreCompletoUsuario, Config.URL_CORREO + token), "text/html");
        System.out.println("Mail Session has been created successfully..");
        // Step3
        System.out.println("\n\n 3rd ===> Get Session and Send mail");
        try (Transport transport = getMailSession.getTransport("smtp")) {
            transport.connect("smtp.gmail.com", EMAIL, PASSWORD);
            transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
        }
    }
}
