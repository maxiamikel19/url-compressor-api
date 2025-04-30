package com.maxiamikel.url_compressor_spring_api.services.impl;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class RandomStringReneratorServiceImpl {

    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    private static final SecureRandom RANDOM = new SecureRandom();

    public String generateRandomString() {

        int length = 5 + RANDOM.nextInt(6);
        List<Character> chars = new ArrayList<>();

        for (char c : CHARACTERS.toCharArray()) {
            chars.add(c);
        }

        Collections.shuffle(chars, RANDOM);
        StringBuilder result = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            result.append(chars.get(i));
        }

        return result.toString();
    }
}
