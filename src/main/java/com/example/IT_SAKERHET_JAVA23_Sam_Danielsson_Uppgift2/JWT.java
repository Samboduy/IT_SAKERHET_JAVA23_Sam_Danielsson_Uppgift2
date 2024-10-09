package com.example.IT_SAKERHET_JAVA23_Sam_Danielsson_Uppgift2;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import lombok.Setter;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Date;

@Getter
@Setter
public class JWT {

    private String generatedJWT;
    private String secretKey;

    public void generateSecretKey(){
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
            keyGen.init(256);
           this.secretKey = keyGen.generateKey().toString();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    public void generateJWT(String userId){
        generateSecretKey();
        String jwt = Jwts.builder()
                .subject(userId)
                .issuer("http://localhost:8080")
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 3600000))
                .signWith(SignatureAlgorithm.HS256, this.secretKey).compact();
        System.out.println("generated JWT: " + jwt);
    }
}
