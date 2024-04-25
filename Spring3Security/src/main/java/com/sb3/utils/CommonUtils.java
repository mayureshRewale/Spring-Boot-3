package com.sb3.utils;

import java.security.SecureRandom;

public class CommonUtils {

    public static String generateRandInt(int length) {
        String numbers = "0123456789";
        SecureRandom randomMethod = new SecureRandom();

        char[] otp = new char[length];
        for (int i = 0; i < length; i++) {
            otp[i] = numbers.charAt(randomMethod.nextInt(numbers.length()));
        }
        return new String(otp);
    }

}
