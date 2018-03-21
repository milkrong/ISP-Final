import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashGeneratorUtils {
    private HashGeneratorUtils() {

    }

    public static String generateMD5 (String message) throws HashGenerationException {
        return hashString(message, "MD5");
    }

    public static String generateSHA1 (String message) throws HashGenerationException {
        return hashString(message, "SHA-1");
    }

    public static String generateSHA256 (String message) throws HashGenerationException {
        return hashString(message, "SHA-256");
    }

    private static String hashString(String message, String hashMethod) throws HashGenerationException {
        try {
            MessageDigest digest = MessageDigest.getInstance(hashMethod);
            byte[] hashBytes = digest.digest(message.getBytes("UTF-8"));

            return convertByteToHex(hashBytes);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            throw new HashGenerationException(
                    "Could not generate hash", ex);
        }
    }

    private static String convertByteToHex(byte[] arrayBytes) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i<arrayBytes.length; i++) {
            stringBuffer.append(Integer.toString((arrayBytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        return stringBuffer.toString();
    }
} 