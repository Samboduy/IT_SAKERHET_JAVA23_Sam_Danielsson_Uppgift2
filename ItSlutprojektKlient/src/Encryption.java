import io.github.cdimascio.dotenv.Dotenv;

import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;


public class Encryption {


    private SecretKey secretKey;

    private final int ITERATIONS = 65536; // Number of iterations
    private final int KEY_LENGTH = 256;



    public String encrypt(String message) {
        Dotenv dotenv = Dotenv.load();
        final String MASTER_KEY = dotenv.get("MASTER_KEY");
        try{
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE,this.secretKey);
            byte[] encryptedData = cipher.doFinal(message.getBytes());
            return Base64.getEncoder().encodeToString(encryptedData);
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            throw new RuntimeException(e);
        }
    }

    public String decrypt(String encryptedData) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, this.secretKey);
        byte[] decodedData = Base64.getDecoder().decode(encryptedData); // Decode from Base64
        byte[] decryptedData = cipher.doFinal(decodedData);
        return new String(decryptedData, "UTF-8");
        }


    public void genKey() throws NoSuchAlgorithmException, InvalidKeySpecException, UnsupportedEncodingException {
        Dotenv dotenv = Dotenv.load();
        final String MASTER_KEY = dotenv.get("MASTER_KEY");

        byte[] keyBytes = Arrays.copyOf(MASTER_KEY.getBytes("UTF-8"), 32); // 32 bytes = 256 bits
        this.secretKey =  new SecretKeySpec(keyBytes, "AES");

    }
}
