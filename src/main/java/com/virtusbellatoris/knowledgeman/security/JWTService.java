package com.virtusbellatoris.knowledgeman.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JWTService {

    @Value("${app.jwt.secret}") // Take the value of app.jwt.secret from Spring configuration and put it into this variable
    private String secretKey;

    @Value("${app.jwt.expiration}") // Again, Spring gets the value from application.properties configuration.
    private Long expiration;

    // Creates a JWT token
    public String generateToken(String email) {
        Date now = new Date();

        return Jwts.builder() // use Jwts.builder() to create token
            .setSubject(email) // set subject to email: who this token is for
            .setIssuedAt(now) // set issuedAt to now: when created
            .setExpiration(new Date(now.getTime() + expiration)) // set expiration to now + expiration: when expires
            .signWith(getSigningKey()) // sign with secret key: sign with secret
            .compact(); // return compact string: build the string
        // new Date(now.getTime() +  is used instead of new Date(System.currentTimeMillis() +
    }

    // The signing key — secret string to be converted to a cryptographic key:
    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // Parse a token and extract subject: Reads email from token
    public String extractEmail(String token) {
        return Jwts.parser()
            .setSigningKey(getSigningKey()) // verify signature
            .parseClaimsJws(token) // parse
            .getBody() // get data inside
            .getSubject(); // get email
    }

    // Checks if token is expired or invalid
    public boolean isTokenValid(String token) {
        try{
            Jwts.parser()
                    .setSigningKey(getSigningKey())
                    .parseClaimsJws(token);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    /*
    // Checking token expiration:
    public boolean isTokenValid(String token) {
        return Jwts.parser()
            .verifyWith(getSigningKey())
            .build()
            .parseSignedClaims(token)
            .getPayload()
            .getExpiration()
            .before(new Date()); // returns true if expired
    }
     */

    /*
    when would getExpiration() be useful?
    Suppose you specifically want to look at the expiration time,
    rather than merely validate the token. For example:
        Claims claims = Jwts.parser()
            .verifyWith(getSigningKey())
            .build()
            .parseSignedClaims(token)
            .getPayload();
        Date expiration = claims.getExpiration();
    also .getExpiration().before(new Date()); // returns true if expired
    Now you have the actual expiration date and can use it for something else.
    For example:
        long remaining = expiration.getTime() - System.currentTimeMillis();
    You could determine: "This token has 17 minutes remaining."
     */


}
