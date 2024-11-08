package com.desafioBack.criptografia.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Service;

@Service
public class EncryptionService {
	 
	public EncryptionService() {
	        
		}
	
	public String hashWithSHA256(String input) {
        try {
           
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            
         
            byte[] hashBytes = digest.digest(input.getBytes());
            
           
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Erro ao inicializar SHA-256", e);
        }
    }
}
