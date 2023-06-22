package dev.gohard.youtubemanager.controller;

import com.google.api.client.auth.oauth2.AuthorizationCodeTokenRequest;
import dev.gohard.youtubemanager.model.dto.GoogleOAuthDto;
import com.google.api.client.auth.oauth2.AuthorizationCodeRequestUrl;
import dev.gohard.youtubemanager.service.GoogleOAuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;

@Controller
@RequiredArgsConstructor
public class GoogleOAuthController {

    private final GoogleOAuthDto googleOAuthDto;
    private final GoogleOAuthService googleOAuthService;

    @GetMapping("/oAuth")
    public String goOauthUri() {
        AuthorizationCodeRequestUrl authorizationUrl = new AuthorizationCodeRequestUrl(
                googleOAuthDto.getAuthorizationUri(),
                googleOAuthDto.getClientId());

        authorizationUrl.setScopes(Collections.singletonList(googleOAuthDto.getScope()));
        authorizationUrl.setResponseTypes(Collections.singletonList(googleOAuthDto.getResponseType()));
        authorizationUrl.setRedirectUri(googleOAuthDto.getRedirectUri());

        return "redirect:" + authorizationUrl.build();
    }

    @GetMapping("/callback")
    public void handleCallback(@RequestParam String code, HttpSession httpSession) {
        String redirectUri = "http://localhost:8080/channels";

        httpSession.setAttribute("accessToken", googleOAuthService.getAccessToken(code, redirectUri));
    }

    @GetMapping("/logout")
    public void logout(HttpSession httpSession) {
        httpSession.removeAttribute("accessToken");
    }

}
