package com.test.project.security.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    public String getToken(UserDetails user) {

        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", getRolesFromUser(user));
        return getToken((HashMap<String, Object>) claims, user);
    }


    private String getToken(HashMap<String,Object> extraClaims, UserDetails user) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()+expiration))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    /*
    private String getRolesFromUser(UserDetails user) {
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        return authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
    }*/

    private List<String> getRolesFromUser(UserDetails user) {
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        return authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
    }

    public String getEmailFromToken(String token) {

        if (StringUtils.hasText(token)) {
            try {
                Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
                return claims.getSubject();
            } catch (Exception ex) {
                // Handle token parsing error
            }
        }
        return null;
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        final String authHeader = request.getHeader("Authorization");

        if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        return null;
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username=getEmailFromToken(token);
        return (username.equals(userDetails.getUsername())&& !isTokenExpired(token));
    }

    public boolean isTokenExpired(String token) {
        try {
            Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
            Date expirationDate = claims.getExpiration();
            return expirationDate.before(new Date()); // Comprueba si la fecha de expiración es anterior a la fecha actual
        } catch (ExpiredJwtException ex) {
            return true; // Token expirado
        } catch (Exception ex) {
            return true; // Error de parseo u otro problema con el token
        }
    }



}
