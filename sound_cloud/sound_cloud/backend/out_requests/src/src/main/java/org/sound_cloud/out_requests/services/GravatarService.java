package org.sound_cloud.out_requests.services;

import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class GravatarService {
    private static final String GRAVATAR_URL = "https://www.gravatar.com/avatar/";

    public String getGravatarUrl(String email) throws NoSuchAlgorithmException {
        String hash = getMd5Hash(email.trim().toLowerCase());
        return GRAVATAR_URL + hash;
    }

    private String getMd5Hash(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(input.getBytes());
        byte[] digest = md.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            sb.append(String.format("%02x", b & 0xff));
        }
        return sb.toString();
    }
}