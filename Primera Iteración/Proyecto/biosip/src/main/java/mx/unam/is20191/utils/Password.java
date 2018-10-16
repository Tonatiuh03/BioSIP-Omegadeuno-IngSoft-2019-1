/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.unam.is20191.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.lang3.RandomStringUtils;

/**
 * Clase de donde se obtiene algunas utilerias para encriptado y para generar
 * cadenas aleatorias.
 *
 * @author Josué Rodrigo Cárdenas Vallarta
 */
public class Password {

    public static String encryptPassword(String password) throws NoSuchAlgorithmException {
        // Create MessageDigest instance for MD5
        MessageDigest md = MessageDigest.getInstance("MD5");
        //Add password bytes to digest
        md.update(password.getBytes());
        //Get the hash's bytes
        byte[] bytes = md.digest();
        //This bytes[] has bytes in decimal format;
        //Convert it to hexadecimal format
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        //Get complete hashed password in hex format
        return sb.toString();
    }

    /**
     * Método que obtiene una cadena aleatoria.
     *
     * @param noCharacters Es el número de caracteres alfanuméricos que
     * requerimos.
     * @return La cadena generada.
     */
    public static String randomString(int noCharacters) {
        boolean useLetters = true;
        boolean useNumbers = true;
        return RandomStringUtils.random(noCharacters, useLetters, useNumbers);
    }

}
