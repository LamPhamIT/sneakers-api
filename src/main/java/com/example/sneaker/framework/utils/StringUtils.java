package com.example.sneaker.framework.utils;

import java.util.Random;

public class StringUtils extends org.springframework.util.StringUtils {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public static String generateKey(int length){
        Random random = new Random();
        StringBuilder activationKey = new StringBuilder(length);

        for(int i = 0; i < length; i++){
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            activationKey.append(randomChar);
        }

        return activationKey.toString();
    }
}
