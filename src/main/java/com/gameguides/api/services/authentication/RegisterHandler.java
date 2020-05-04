package com.gameguides.api.services.authentication;

import com.gameguides.api.models.Account;
import com.gameguides.api.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RegisterHandler {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PasswordHasher hasher;

    public void registerUser(String username, String password, String email) throws Exception {
        Account account = new Account();
        String hashedPassword = null;

        try {
            hashedPassword = hasher.generateStrongPassword(password);
        } catch (Exception exc) {
            exc.printStackTrace();
            throw exc;
        }
        account.uuid = UUID.randomUUID();
        account.username = username;
        account.password = hashedPassword;
        account.email = email;

        accountRepository.save(account);
    }
}
