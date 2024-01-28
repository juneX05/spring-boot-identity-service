package com.mwambacodes.identityservice.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.sql.Date;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

@Service
public class JWTUtil {

    @Value("#{'${jwt.secret_key}'}")
    private String SECRET_KEY;

    @Value("#{'${jwt.issuer}'}")
    private String ISSUER;

    @Value("#{'${jwt.expiration_days}'}")
    private Long EXPIRATION_DAYS;

    public SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public String issueToken(
            Map<String, Object> extraClaims,
            String username
    ) {
        return Jwts.builder()
                .claims(extraClaims)
                .subject(username)
                .issuer(ISSUER)
                .issuedAt(Date.from(Instant.now()))
                .expiration(Date.from(Instant.now().plus(EXPIRATION_DAYS, ChronoUnit.DAYS)))
                .signWith(getSigningKey())
                .compact();
    }

    public String issueToken(
            String username,
            List<String> scopes
    ) {
        return issueToken(Map.of("scopes", scopes), username);
    }

    public String getSubject(String token) {
        return getClaims(token).getSubject();
    }

    public Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean isTokenValid(String token, String username) {
        String subject = getSubject(token);
        return subject.equals(username) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return getClaims(token).getExpiration().before(Date.from(Instant.now()));
    }
}
