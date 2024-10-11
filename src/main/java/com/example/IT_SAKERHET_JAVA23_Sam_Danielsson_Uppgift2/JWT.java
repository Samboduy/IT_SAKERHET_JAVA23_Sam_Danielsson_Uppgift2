package com.example.IT_SAKERHET_JAVA23_Sam_Danielsson_Uppgift2;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
@Getter
@Setter
public class JWT {

    private String generatedJWT;


    /*public void generateJWT(String userId, SecretKey secretKey){
        this.generatedJWT = Jwts.builder()
                .subject(userId)
                .issuer("http://localhost:8080")
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 3600000)).signWith(secretKey).compact();
        System.out.println("generated JWT: " + generatedJWT);
    }*/

    public void generateJWT(String email, SecretKey secretKey){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey.getEncoded());
            this.generatedJWT = com.auth0.jwt.JWT.create()
                    .withIssuer("localhost:8080")
                    .withSubject(email)
                    .withIssuedAt(new Date())
                    .withExpiresAt(new Date(System.currentTimeMillis() + 3600000))
                    .sign(algorithm);
            System.out.println("Generated JWT: " + this.generatedJWT);
            verifyJWT(generatedJWT,secretKey);
        }catch (JWTCreationException e){
            System.out.println("JWT creation failed " + e.getMessage() );
        }

    }

    public DecodedJWT verifyJWT(String token, SecretKey secretKey){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secretKey.getEncoded());
            JWTVerifier verifier = com.auth0.jwt.JWT.require(algorithm)
                    .acceptLeeway(10)
                    .withIssuer("localhost:8080")
                    .build();

            DecodedJWT decodedJWT = verifier.verify(token);
            System.out.println("Decoded JWT: " + decodedJWT.getSubject());
            return decodedJWT;
        }catch (JWTVerificationException e){
            System.out.println("JWT verification failed " + e.getMessage() );
            return null;
        }
    }





    public Claims extractAllClaims(String token, String secretKey) {
        try {
            System.out.println("token: " + token);
            return Jwts.parser()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getPayload();
        }catch (ExpiredJwtException e){
            System.out.println("Expired JWT token " + e);
            return null;
        }

    }

    public Date extractExpiration(String token, String secretKey) {
        return extractAllClaims(token, secretKey).getExpiration();
    }

    public Boolean isTokenExpired(String token,String secretKey) {
        return extractExpiration(token,secretKey).before(new Date());
    }




}
