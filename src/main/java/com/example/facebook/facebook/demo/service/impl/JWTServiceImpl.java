package com.example.facebook.facebook.demo.service.impl;

import com.example.facebook.facebook.demo.service.JWTService;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTServiceImpl implements JWTService{

    /*
    Values are injected from the application.properties file
     */
    @Value("${application.security.jwt.secret-key}")
    private String secretKey;
    @Value("${application.security.jwt.expiration}")
    private long jwtExpiration;
    @Value("${application.security.jwt.refresh-token.expiration}")
    private long refreshExpiration;

    /**
     *  This method is used to generate the token with 1 parameter:
     * @param userDetails
     * @return
     */
    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(), userDetails);
    }
    /**
    This method is used to generate
     the token with 2 parameters(If we need to add extra claims):
    * @param extraClaims- claims to be added to the token
    * @param userDetails- user details
    * @return - returns the token
     */
    public String generateToken(Map<String,Object> extraClaims, UserDetails userDetails){
        return buildToken(extraClaims, userDetails, jwtExpiration);
    }


    /**
    This method is used to generate the refresh token
    * @param userDetails- user details
    * @return - returns the refresh token
     */
    public String generateRefreshToken(UserDetails userDetails){

        return buildToken(new HashMap<>(), userDetails,refreshExpiration);
    }


    /**
     * @param extractClaims- claims to be added to the token
     * @param userDetails-  user details
     * @param expiration-   expiration time of the token
     * @return - returns the token
     */
    private String buildToken(
            Map<String,Object> extractClaims,
            UserDetails userDetails,
            long  expiration
    ){
        return  Jwts
                .builder()
                .setClaims(extractClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+expiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /*
    This method is used to extract
     the username from the token
     */
    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }

    /*
    This method is used to return the claims in type T
     depending on the function passed
     */
    public <T> T extractClaim(String token, Function<Claims,T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     *  This method is used to extract all the claims
     *      from the token and store it in the claims object
     * @param token- token to be validated
     * @param userDetails- user details
     * @return - returns true if the token is valid
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * This method is used to validate the token
     * @param token
     * @param userDetails
     * @return
     */
    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    /**
     * This method is used to check if expiration
     * of the token is before the current date
     * @param token
     * @param userDetails
     * @return
     */
    private boolean isTokenExpired(String token) {
        return extractClaim(token,claims -> claims.getExpiration()).before(new Date());
    }

    /*
    This method is used to hash the secret key
     */
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
