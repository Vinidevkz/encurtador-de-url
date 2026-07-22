package tools;

import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;

public class Base62Encoder {

    private static final String alphabet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final int base = alphabet.length();

    public static String encode(String originalUrl) throws NoSuchAlgorithmException {
        try {
            String input = originalUrl + Instant.now().toEpochMilli();

            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hashBytes = md.digest(input.getBytes());

            ByteBuffer buffer = ByteBuffer.wrap(hashBytes);
            long numericValue = Math.abs(buffer.getLong());

            return encodeBase62(numericValue);

        }catch (NoSuchAlgorithmException e){
            throw new RuntimeException("Erro ao gerar hash", e);
        }

    }

    private static String encodeBase62(long input) {
        StringBuilder sb = new StringBuilder();

        while (input > 0) {
            sb.append(alphabet.charAt((int) (input % base)));
            input /= base;
        }

        while (sb.length() < 7) {
            sb.append('0');
        }

        return sb.reverse().substring(0, 7);
    }
}
