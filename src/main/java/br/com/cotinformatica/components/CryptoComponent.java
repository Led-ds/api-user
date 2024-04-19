package br.com.cotinformatica.components;

import org.springframework.stereotype.Component;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


@Component
public class CryptoComponent {
	
   /*
    * Método para receber um valor e retorna-lo
    * criptografado no padrão SHA 256
    */
   public String sha256Encrypt(String value) {
       try {
           // Create a SHA-256 digest
           MessageDigest digest = MessageDigest.getInstance("SHA-256");
          
           // Perform the hashing
           byte[] encodedhash = digest.digest(value.getBytes(java.nio.charset.StandardCharsets.UTF_8));
          
           // Convert the hash into a hexadecimal string
           return bytesToHex(encodedhash);
       } catch (NoSuchAlgorithmException e) {
           throw new RuntimeException("SHA-256 algorithm not found", e);
       }
   }
  
   // Helper method to convert byte array into a hexadecimal string
   private String bytesToHex(byte[] hash) {
       StringBuilder hexString = new StringBuilder(2 * hash.length);
       for (int i = 0; i < hash.length; i++) {
           String hex = Integer.toHexString(0xff & hash[i]);
           if(hex.length() == 1) {
               hexString.append('0');
           }
           hexString.append(hex);
       }
       return hexString.toString();
   }
}

