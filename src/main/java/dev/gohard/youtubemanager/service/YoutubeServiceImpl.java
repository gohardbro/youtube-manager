package dev.gohard.youtubemanager.service;

import com.google.api.services.youtube.model.Playlist;
import com.google.api.services.youtube.model.Video;
import dev.gohard.youtubemanager.util.YoutubeChannelResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

import static dev.gohard.youtubemanager.util.YoutubeChannelResponse.*;

@Service
@RequiredArgsConstructor
public class YoutubeServiceImpl implements YoutubeService {
    @Override
    public List<Video> getLikedVideos(String accessToken) {
        return null;
    }

    @Override
    public List<Playlist> getPlaylists(String accessToken) {
        return null;
    }

    @Override
    public List<YoutubeChannel> getChannels(String accessToken) {
        WebClient webClient = WebClient.builder()
                .baseUrl("https://www.googleapis.com/youtube/v3")
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        return webClient.get()
                .uri(builder -> builder.path("/channels")
                        .queryParam("part", "snippet,contentDetails")
                        .queryParam("mine", "true")
                        .build())
                .retrieve()
                .bodyToMono(YoutubeChannelResponse.class)
                .block().getItems();
    }
}