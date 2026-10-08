package com.fatesg.devweb.aula08.loja.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    private final SecretKey chave;
    private final Duration validade;

    public JwtService(@Value("${app.jwt.segredo}") String segredo,
                      @Value("${app.jwt.expiracao-minutos}") long minutos) {
        this.chave = Keys.hmacShaKeyFor(Decoders.BASE64.decode(segredo));
        this.validade = Duration.ofMinutes(minutos);
    }

    public String gerar(String email) {
        Instant agora = Instant.now();
        return Jwts.builder()
                .subject(email)
                .issuedAt(Date.from(agora))
                .expiration(Date.from(agora.plus(validade)))
                .signWith(chave)
                .compact();
    }

    // Lança JwtException se o token for inválido, adulterado ou estiver expirado
    public String extrairEmail(String token) {
        return Jwts.parser()
                .verifyWith(chave)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }
}
