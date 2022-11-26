package com.example.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class OneWayEncryptor {
    private final static String VERSION = "SHA-512";
    private final static String SALT = "afb00438-e98b-48dd-acee-45d3e1fc36c4";

    public static String hashFrom(String string){
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance(VERSION);
            md.update(SALT.getBytes(StandardCharsets.UTF_8));
            byte[] bytes = md.digest(string.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }
}