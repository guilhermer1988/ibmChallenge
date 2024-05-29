package com.ibmchallengejava.ibmchallenge.auth;

import com.ibmchallengejava.ibmchallenge.model.OAuth2TokenResponse;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
public class OAuth2TokenProvider {
    private static final String TOKEN_URL = "https://seuprovedor/oauth/token";
    private static final String CLIENT_ID = "seu_client_id";
    private static final String CLIENT_SECRET = "seu_client_secret";
    private static final String USERNAME = "seu_usuario";
    private static final String PASSWORD = "sua_senha";
    private static final String GRANT_TYPE = "password";

    public String getToken() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", GRANT_TYPE);
        body.add("client_id", CLIENT_ID);
        body.add("client_secret", CLIENT_SECRET);
        body.add("username", USERNAME);
        body.add("password", PASSWORD);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);

        ResponseEntity<OAuth2TokenResponse> response = new RestTemplate().postForEntity(TOKEN_URL, request, OAuth2TokenResponse.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody().getAccessToken();
        } else {
            throw new RuntimeException("Failed to obtain OAuth2 token");
        }
    }

    public static void main(String[] args) {
        OAuth2TokenProvider tokenProvider = new OAuth2TokenProvider();
        String accessToken = tokenProvider.getToken();
        System.out.println("Access Token: " + accessToken);
    }
}
