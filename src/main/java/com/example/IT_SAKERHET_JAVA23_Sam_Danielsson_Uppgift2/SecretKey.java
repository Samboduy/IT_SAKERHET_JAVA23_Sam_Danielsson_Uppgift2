package com.example.IT_SAKERHET_JAVA23_Sam_Danielsson_Uppgift2;

import lombok.Getter;
import lombok.Setter;

import javax.crypto.KeyGenerator;


@Setter
@Getter
public class SecretKey {


    private javax.crypto.SecretKey secretKey;


    public void generateSecretKey(){
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
            keyGen.init(256);
            this.secretKey =  keyGen.generateKey();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}
