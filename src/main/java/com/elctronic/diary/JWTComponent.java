package com.elctronic.diary;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTComponent {
    @Value("${app.secret}")
    private String secret;

    @Value("${app.secret.experationMm}")
    private int lifeTime;

    public String generationToken(Authentication authentication) {
        UserDetailImplementation userDetail = (UserDetailImplementation) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject((userDetail.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + lifeTime))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public String getNameFromJWT(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJwt(token).getBody().getSubject();
    }

}
