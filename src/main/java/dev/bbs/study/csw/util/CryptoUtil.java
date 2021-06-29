package dev.bbs.study.csw.util;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CryptoUtil {
    private CryptoUtil() {

    }

    public static class Sha512 {
        private Sha512() {

        }
        public static String hash(String input) {
            try {
                MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
                messageDigest.reset();
                messageDigest.update(input.getBytes(StandardCharsets.UTF_8));
                return String.format("%0128x", new BigInteger(1, messageDigest.digest()));
            } catch (NoSuchAlgorithmException ex) {
                return null;
            }
        }
    }


}
