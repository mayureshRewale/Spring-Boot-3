package com.sb3.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;

@Slf4j
@Component
public class EncryptionUtils {

    public static String encrypt(String plainText, String secreteKey,  String algorithm) throws Exception {
        Key key = generateKey(secreteKey.getBytes(), algorithm);

        Cipher chiper = Cipher.getInstance(algorithm);
        chiper.init(Cipher.ENCRYPT_MODE, key);

        byte[] encVal = chiper.doFinal(plainText.getBytes());

        return Base64.getEncoder().encodeToString(encVal);
    }

    public static String decrypt(String encryptedText, String secreteKey,  String algorithm) throws Exception {
        Key key = generateKey(secreteKey.getBytes(), algorithm);

        Cipher chiper = Cipher.getInstance(algorithm);
        chiper.init(Cipher.DECRYPT_MODE, key);

        byte[] decordedValue = Base64.getDecoder().decode(encryptedText);
        byte[] decValue = chiper.doFinal(decordedValue);

        return new String(decValue);
    }

    private static Key generateKey(byte[] keyVal, String algorithm) throws Exception {
        return new SecretKeySpec(keyVal, algorithm);
    }

}
