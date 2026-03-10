package com.alurachallenge.forohub.infra.errores.security;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.alurachallenge.forohub.domain.usuario.Usuario;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {


    @Value("${api.security.token.secret}")
    private String secret;

    public String generarToken(Usuario usuario) {
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("API forohub")
                    .withSubject(usuario.getCorreoElectronico())
                    .withExpiresAt(generarFechaExpiracion())
                    .sign(algoritmo);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error al crear el token", exception);
        }
    }

    public String getSubject(String tokenJWT) {
        if (tokenJWT == null || tokenJWT.isBlank()) {
            throw new RuntimeException("El token está vacío");
        }
        try {
            Algorithm algoritmo = Algorithm.HMAC256(secret);
            return JWT.require(algoritmo)
                    .withIssuer("API forohub")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            System.out.println("Fallo en la verificación: " + exception.getMessage());
            return null;
        }
    }

    // Metodo auxiliar para la expiración (2h)
    private Instant generarFechaExpiracion() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
    }
}