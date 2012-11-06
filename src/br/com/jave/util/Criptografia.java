package br.com.jave.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Criptografia {

    //Fun��o para criar hash do texto informado  
    public static String md5(String texto){  
        String sen = "";  
        MessageDigest md = null;  
        try {  
            md = MessageDigest.getInstance("MD5");  
        } catch (NoSuchAlgorithmException e) {  
            e.printStackTrace();  
        }  
        BigInteger hash = new BigInteger(1, md.digest(texto.getBytes()));  
        sen = hash.toString(16);              
        return sen;  
    }  
	
}
