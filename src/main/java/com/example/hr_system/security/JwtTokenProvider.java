package com.example.hr_system.security;

import com.example.hr_system.exception.HrSystemAPIException;
import com.example.hr_system.service.auth.UserDetailslmpl;
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

    @Value("${app-jwt-secret}")
    private String jwtSecret;

    @Value("${app-jwt-expiration-milliseconds}")
    private long jwtExpirationTime;

    public String generationToken(Authentication authentication){
        UserDetailslmpl user = (UserDetailslmpl) authentication.getPrincipal();

        long nowMillis = System.currentTimeMillis();
        Date nowTime = new Date(nowMillis);

        long expMills = System.currentTimeMillis() + jwtExpirationTime;
        Date expTime = new Date(expMills);


        String token = Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(nowTime)
                .setExpiration(expTime)
                .signWith(key())
                .compact();


        return token;
    }

    public String refreshToken(String token){
        final Date createdDate = new Date(new Date().getTime());
        final Date expirationDate = calculateExpirationDate(createdDate);

        final Claims claims = Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody();

        claims.setIssuedAt(createdDate);
        claims.setExpiration(expirationDate);

        return Jwts.builder().setClaims(claims).signWith(key()).compact();

    }

    private Date calculateExpirationDate(Date createdDate){
        return new Date(createdDate.getTime() + jwtExpirationTime);
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
