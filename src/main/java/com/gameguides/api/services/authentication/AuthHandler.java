package com.gameguides.api.services.authentication;

import com.gameguides.api.models.Account;
import com.gameguides.api.repository.AccountRepository;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Component
public class AuthHandler {
    @Autowired
    private HashVerifyer validator;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TokenHelper tokenHelper;

    private String token;

    public boolean validateLoginAttempt(String username, String password)
    {
        try {
            for(Account account : accountRepository.findAll())
            {
                if(account.username.equals(username))
                {
                    if(validator.validatePassword(password, account.password))
                    {
                        token = tokenHelper.issueToken(account.username, account.uuid.toString());
                        return true;
                    }
                }
            }
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean validateTokenAuthAttempt(String token)
    {
        if(token.isEmpty()) return false;
        try {
            tokenHelper.verifyToken(token);
        } catch (JwtException exc) {
            return false;
        }
        return true;
    }

    public String getToken() {
        return token;
    }
}
