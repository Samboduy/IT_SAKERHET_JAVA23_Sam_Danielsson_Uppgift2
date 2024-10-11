package com.example.IT_SAKERHET_JAVA23_Sam_Danielsson_Uppgift2;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import java.util.Arrays;


@Setter
@Getter
@Service
public class SecretKey {


    private javax.crypto.SecretKey secretKey;


    public void generateSecretKey(){
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
            keyGen.init(256);
            this.secretKey =  keyGen.generateKey();
            System.out.println(Arrays.toString(this.secretKey.getEncoded()));
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}
