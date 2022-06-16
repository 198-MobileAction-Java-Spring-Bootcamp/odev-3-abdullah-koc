package com.example.hwthree.jwt.security;


import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenGenerator {

    @Value("${jwt.security.expire.time}")
    private Long EXPIRE_TIME;

    @Value("${jwt.security.app.key}")
    private String APP_KEY;

    public String generateJwtToken(Authentication authentication) {
        JwtUserDetails jwtUserDetails = (JwtUserDetails) authentication.getPrincipal();

        Date expireDate = new Date(new Date().getTime() + EXPIRE_TIME);

        return Jwts.builder()
                .setSubject(Long.toString(jwtUserDetails.getId()))
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, APP_KEY)
                .compact();
    }

    public Long findUserIdByToken(String token){

        Jws<Claims> claimsJws = parseToken(token);

        String userId = claimsJws.getBody().getSubject();

        return Long.parseLong(userId);
    }

    private Jws<Claims> parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(APP_KEY)
                .parseClaimsJws(token);
    }

    public boolean validateToken(String token) {

        boolean isValid;
        try {
            Jws<Claims> claimsJws = parseToken(token);
            boolean tokenExpired = isTokenExpired(claimsJws);

            isValid = !tokenExpired;
        } catch (Exception e) {
            isValid = false;
        }

        return isValid;
    }

    public boolean isTokenExpired(Jws<Claims> claimsJws){

        Date expireDate = claimsJws.getBody().getExpiration();

        return expireDate.before(new Date());
    }

}
