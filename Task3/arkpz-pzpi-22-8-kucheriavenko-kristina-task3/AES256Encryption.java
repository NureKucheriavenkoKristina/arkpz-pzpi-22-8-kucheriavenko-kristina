package com.BiologicalMaterialsSystem;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class AES256Encryption {

    private static final String FIXED_KEY = "1234567890abcdef1234567890abcdef"; // 32 байти = 256 біт

    public static SecretKey getSecretKey() {
        byte[] decodedKey = Base64.getDecoder().decode(FIXED_KEY); // декодуємо з Base64
        return new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
    }

    public static String encrypt(String password) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, getSecretKey());
        byte[] encryptedBytes = cipher.doFinal(password.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }
}
