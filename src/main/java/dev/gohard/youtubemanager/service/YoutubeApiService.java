package dev.gohard.youtubemanager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class YoutubeApiService {

    private final WebClient webClient;
    public ResponseEntity<String> getChannels(String accessToken) {
        String url = "https://www.googleapis.com/youtube/v3/channels?part=snippet&mine=true";

        return webClient.get()
                .uri(url)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .retrieve()
                .toEntity(String.class)
                .block();
    }
}
