package com.gameguides.api.services.authentication;

import com.gameguides.api.models.Account;
import com.gameguides.api.models.LolGuide;
import com.gameguides.api.repository.AccountRepository;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
class TokenHelper {

    private static final SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    @Autowired
    private AccountRepository accountRepository;

    String issueToken(String username, String clientid) {
        String payload = Jwts.builder()
                .setIssuer("auth-server")
                .setIssuedAt(new Date())
                .setSubject(username)
                .claim("clientid", clientid)
                .signWith(key)
                .claim("guides", getInterests(clientid))
                .signWith(key)
                .compact();
        return payload;
    }

    private String getInterests(String clientid) {
        Account ac = accountRepository.findOneByUuid(UUID.fromString(clientid));
        List<String> ids = new ArrayList<>();
        for(LolGuide guide : ac.lolGuides) {
            ids.add(guide.uuid.toString());
        }
        return ids.toString();
    }

    void verifyToken(String jwsToken){
        Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(jwsToken);
    }
}
