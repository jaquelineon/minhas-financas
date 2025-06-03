package com.viana.minhas_financas.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.viana.minhas_financas.enums.Role;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.expiration}")
    private long expirationTime;

    public String gerarToken(String emailUsuario, Role role) {
        return JWT.create()
                .withSubject(emailUsuario)
                .withIssuer(issuer)
                .withClaim("role", role.name())
                .withExpiresAt(new Date(System.currentTimeMillis() + expirationTime))
                .sign(Algorithm.HMAC256(secret));
    }

    public String validarToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secret);

        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer(issuer)
                .build();

        DecodedJWT decodedJWT = verifier.verify(token);
        return decodedJWT.getSubject();
    }

    public String getClaim(String token, String claimKey) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer(issuer)
                .build();

        DecodedJWT decodedJWT = verifier.verify(token);
        return decodedJWT.getClaim(claimKey).asString();
    }
}
