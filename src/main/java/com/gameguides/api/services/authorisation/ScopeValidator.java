package com.gameguides.api.services.authorisation;


import com.gameguides.api.services.authentication.AuthHandler;
import com.gameguides.api.services.utils.JWTDecoder;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ScopeValidator {
    @Autowired
    private AuthHandler authHandler;

    public boolean isOwner(String authToken, String accessResourceId) {
        if (authHandler.validateTokenAuthAttempt(authToken)) {
            JSONObject decodedToken = JWTDecoder.decodeJWTPayLoad(authToken);
            return decodedToken.getString("guides").contains(accessResourceId);
        }
        return false;
    }
}
