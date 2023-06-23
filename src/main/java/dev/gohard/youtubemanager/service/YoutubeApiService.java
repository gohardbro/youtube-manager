package dev.gohard.youtubemanager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class YoutubeApiService {

    private final WebClient webClient;
    public ResponseEntity<String> getChannels(String accessToken) {
        String url = "https://www.googleapis.com/youtube/v3/channels";
//        String url = "https://www.googleapis.com/youtube/v3/playlistItems";

        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("part", "snippet");
        queryParams.add("part", "contentDetails");
        queryParams.add("mine", "true");

        String resultUrl = UriComponentsBuilder.fromHttpUrl(url)
                .queryParams(queryParams)
                .toUriString();

        return webClient.get()
                .uri(resultUrl)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .retrieve()
                .toEntity(String.class)
                .block();
    }
}
