package com.mycompany.kedaingombeapp.util;

import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class PasswordUtil {
    private static final String PREFIX = "pbkdf2";
    private static final int ITERASI = 120000;
    private static final int PANJANG_SALT = 16;   // byte
    private static final int PANJANG_HASH = 256;  // bit

    // Hasilkan string "pbkdf2$iterasi$salt$hash" untuk disimpan di database
    public static String hash(String password) {
        byte[] salt = new byte[PANJANG_SALT];
        new SecureRandom().nextBytes(salt);
        byte[] hash = pbkdf2(password, salt, ITERASI);
        return PREFIX + "$" + ITERASI + "$"
                + Base64.getEncoder().encodeToString(salt) + "$"
                + Base64.getEncoder().encodeToString(hash);
    }

    // Cek password input terhadap string hash dari database
    public static boolean verify(String password, String tersimpan) {
        if (!sudahDihash(tersimpan)) return false;

        try {
            String[] bagian = tersimpan.split("\\$");
            int iterasi = Integer.parseInt(bagian[1]);
            byte[] salt = Base64.getDecoder().decode(bagian[2]);
            byte[] hashAsli = Base64.getDecoder().decode(bagian[3]);

            byte[] hashInput = pbkdf2(password, salt, iterasi);
            return MessageDigest.isEqual(hashAsli, hashInput);   // perbandingan constant-time
        } catch (IllegalArgumentException e) {
            return false;   // format rusak
        }
    }

    // Dipakai migrasi: true kalau string sudah berformat hash
    public static boolean sudahDihash(String s) {
        return s != null && s.startsWith(PREFIX + "$") && s.split("\\$").length == 4;
    }

    private static byte[] pbkdf2(String password, byte[] salt, int iterasi) {
        try {
            PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterasi, PANJANG_HASH);
            return SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256").generateSecret(spec).getEncoded();
        } catch (GeneralSecurityException e) {
            throw new IllegalStateException("PBKDF2 tidak tersedia", e);
        }
    }
}