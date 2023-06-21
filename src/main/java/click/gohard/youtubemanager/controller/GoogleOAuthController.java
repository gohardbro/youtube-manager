package click.gohard.youtubemanager.controller;

import click.gohard.youtubemanager.model.dto.GoogleOAuthDto;
import com.google.api.client.auth.oauth2.AuthorizationCodeRequestUrl;
import lombok.RequiredArgsConstructor;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.Collections;

@Controller
@RequiredArgsConstructor
public class GoogleOAuthController {

    private final GoogleOAuthDto googleOAuthDto;
    private final StringEncryptor encryptor;

    @GetMapping("/oAuth")
    public String goOauthUri() {
        AuthorizationCodeRequestUrl authorizationUrl = new AuthorizationCodeRequestUrl(
                googleOAuthDto.getAuthorizationUri(),
                encryptor.decrypt(googleOAuthDto.getClientId()));

        authorizationUrl.setScopes(Collections.singletonList(googleOAuthDto.getScope()));
        authorizationUrl.setResponseTypes(Collections.singletonList(googleOAuthDto.getResponseType()));
        authorizationUrl.setRedirectUri(googleOAuthDto.getRedirectUri());

        return "redirect:" + authorizationUrl.build();
    }

    @GetMapping("/callback")
    public String handleCallback() {

        return "redirect:/videos";
    }


}
