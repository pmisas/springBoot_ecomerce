package com.test.project.security;

import io.jsonwebtoken.*;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class tokenUtils {
    private final static String ACCESS_TOKEN_SECRET = "1234qWe098pOi";
    private final static Long ACCESS_TOKEN_VALIDITY_SECONDS = 86_400L; //1dia

    public static String createToken(String name, String email) {
        long expirationTime = ACCESS_TOKEN_VALIDITY_SECONDS * 1_000;
        Date expirationDate = new Date(System.currentTimeMillis() + expirationTime);

        Map<String, Object> extra = new HashMap<>();
        extra.put("name", name);

        SecretKey secretKey = new SecretKeySpec(ACCESS_TOKEN_SECRET.getBytes(), SignatureAlgorithm.HS256.getJcaName());

        return Jwts.builder()
                .setSubject(email)
                .setExpiration(expirationDate)
                .addClaims(extra)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();

    }
public static UsernamePasswordAuthenticationToken getAuthentication(String token){
    try {
        Claims claims = Jwts.parser()
                .setSigningKey(ACCESS_TOKEN_SECRET)
                .parseClaimsJwt(token)
                .getBody();

        String email = claims.getSubject();

        return new UsernamePasswordAuthenticationToken(email, null, Collections.emptyList());
    } catch (JwtException e) {
        return null;
    }
}

}
