package com.example.hr_system.security;

import com.example.hr_system.exception.HrSystemAPIException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("223310237197312893")
    private String jwtSecret;

    @Value("60480000")
    private String jwtExpirationTime;

    public String generationToken(Authentication authentication){
        String username = authentication.getName();

        Date currentDate = new Date();

        Date expireDate = new Date(currentDate.getTime()+jwtExpirationTime);

        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(key())
                .compact();


        return token;
    }

    private Key key(){
        return Keys.hmacShaKeyFor(
                Decoders.BASE64.decode(jwtSecret)
        );
    }

    public String getUsername(String token){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody();

        String username = claims.getSubject();
        return username;

    }


    public boolean validationToken(String token){
        try{
            Jwts.parserBuilder()
                    .setSigningKey(key())
                    .build()
                    .parse(token);
            return true;
        }catch (MalformedJwtException exception){
            throw new HrSystemAPIException(HttpStatus.BAD_REQUEST, "Invalid JWT token");
        }catch (ExpiredJwtException exception){
            throw new HrSystemAPIException(HttpStatus.BAD_REQUEST,"Expired JWT token");
        }catch (UnsupportedJwtException exception){
            throw new HrSystemAPIException(HttpStatus.BAD_REQUEST, "Unsupported JWT token");
        }catch (IllegalArgumentException exception){
            throw new HrSystemAPIException(HttpStatus.BAD_REQUEST, "JWT claims String is empty");
        }
    }


}
