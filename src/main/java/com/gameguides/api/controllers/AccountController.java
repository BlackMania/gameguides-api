package com.gameguides.api.controllers;

import com.gameguides.api.DTO.LoginDTO;
import com.gameguides.api.DTO.RegisterDTO;
import com.gameguides.api.services.authentication.AuthHandler;
import com.gameguides.api.services.authentication.RegisterHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gg/auth")
public class AccountController {

    @Autowired
    private AuthHandler authHandler;
    @Autowired
    private RegisterHandler registerHandler;


    @PostMapping("/login")
    public String login(@RequestBody LoginDTO loginDTO) {
        if (authHandler.validateLoginAttempt(loginDTO.username, loginDTO.password)) {
            return authHandler.getToken();
        } else {
            return "Invalid login attempt";
        }
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterDTO registerDTO)
    {
        try {
            registerHandler.registerUser(registerDTO.username, registerDTO.password, registerDTO.email);
            return "Sucesfully created";
        } catch (Exception e) {
            e.printStackTrace();
            return "An error occured trying to create your account.";
        }
    }
}
