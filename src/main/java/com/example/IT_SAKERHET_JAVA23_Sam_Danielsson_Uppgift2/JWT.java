package com.example.IT_SAKERHET_JAVA23_Sam_Danielsson_Uppgift2;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import java.util.Date;


public class JWT {

    public String getGeneratedJWT() {
        return generatedJWT;
    }

    public void setGeneratedJWT(String generatedJWT) {
        this.generatedJWT = generatedJWT;
    }

    private String generatedJWT;


    public void generateJWT(String userId, SecretKey secretKey){
        this.generatedJWT = Jwts.builder()
                .subject(userId)
                .issuer("http://localhost:8080")
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 3600000)).signWith(secretKey).compact();
        System.out.println("generated JWT: " + generatedJWT);
    }


}
