package dev.gohard.youtubemanager.model.dto;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class GoogleOAuthDto {
    @Value("${oOauth.client-id}")
    private String clientId;

    @Value("${oOauth.client-secret}")
    private String clientSecret;

    @Value("authorization_code")
    private String grantType;

    @Value("http://localhost:8080/callback")
    private String redirectUri;

    @Value("code")
    private String responseType;

    @Value("${oOauth.scope}")
    private String scope;

    @Value("offline")
    private String accessType;

    @Value("${oOauth.provider.authorization-uri}")
    private String authorizationUri;

    @Value("${oOauth.provider.token-uri}")
    private String tokenUri;

    @Value("${oOauth.provider.user-info-uri}")
    private String userInfoUri;

    @Value("${oOauth.provider.user-name-attribute}")
    private String userNameAttribute;
}
