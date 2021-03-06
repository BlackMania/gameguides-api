package com.gameguides.api.services.utils;

import org.apache.tomcat.util.codec.binary.Base64;
import org.json.JSONObject;

public class JWTDecoder {
    public static JSONObject decodeJWTPayLoad(String token)
    {
        String payload = token.split("\\.")[1];
        byte[] byteArray = Base64.decodeBase64(payload.getBytes());
        return new JSONObject(new String(byteArray));
    }

    public static String getSubFromPayload(String token) {
        String payload = token.split("\\.")[1];
        byte[] byteArray = Base64.decodeBase64(payload.getBytes());
        JSONObject decToken = new JSONObject(new String(byteArray));
        return decToken.getString("sub");
    }

    public static String getClientIdFromPayload(String token) {
        String payload = token.split("\\.")[1];
        byte[] byteArray = Base64.decodeBase64(payload.getBytes());
        JSONObject decToken = new JSONObject(new String(byteArray));
        return decToken.getString("clientid");
    }
}
