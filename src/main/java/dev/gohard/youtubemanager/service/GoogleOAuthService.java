package dev.gohard.youtubemanager.service;

import com.google.api.client.auth.oauth2.TokenResponse;
import dev.gohard.youtubemanager.model.dto.GoogleOAuthDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class GoogleOAuthService {

    private final WebClient webClient;
    private final GoogleOAuthDto googleOAuthDto;

    public String getAccessToken(String code, String redirectUri) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("client_id", googleOAuthDto.getClientId());
        params.add("client_secret", googleOAuthDto.getClientSecret());
        params.add("code", code);
        params.add("grant_type", googleOAuthDto.getGrantType());
        params.add("redirect_uri", redirectUri);

        TokenResponse tokenResponse = webClient.method(HttpMethod.POST)
                .uri(googleOAuthDto.getTokenUri())
                .body(BodyInserters.fromFormData(params))
                .retrieve()
                .bodyToMono(TokenResponse.class)
                .block();

        String accessToken = null;
        if (tokenResponse != null)
            accessToken = tokenResponse.getAccessToken();

        return accessToken;
    }
}
