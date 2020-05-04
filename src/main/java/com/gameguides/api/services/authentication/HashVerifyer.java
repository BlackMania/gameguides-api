package com.gameguides.api.services.authentication;

import org.springframework.stereotype.Component;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Component
class HashVerifyer {
    boolean validatePassword(String originalPassword, String storedPassword) throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        String[] parts = storedPassword.split(":");
        int storedIterations = Integer.parseInt(parts[0]);
        byte[] storedSalt = fromHex(parts[1]);
        byte[] storedHash = fromHex(parts[2]);

        PBEKeySpec spec = new PBEKeySpec(originalPassword.toCharArray(), storedSalt, storedIterations, storedHash.length * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] userInputHash = skf.generateSecret(spec).getEncoded();

        int diff = storedHash.length ^ userInputHash.length;
        for(int i = 0; i < storedHash.length && i < userInputHash.length; i++)
        {
            diff += storedHash[i] ^ userInputHash[i];
        }
        return diff == 0;
    }

    private byte[] fromHex(String hex)
    {
        byte[] bytes = new byte[hex.length() / 2];
        for(int i = 0; i<bytes.length ;i++)
        {
            bytes[i] = (byte)Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return bytes;
    }
}
