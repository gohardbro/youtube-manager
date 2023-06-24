package dev.gohard.youtubemanager.config;

import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.youtube.YouTube;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class YoutubeConfig {

    @Bean
    public HttpRequestInitializer httpRequestInitializer() {
        return new HttpRequestInitializer() {
            @Override
            public void initialize(com.google.api.client.http.HttpRequest request) {
            }
        };
    }

    @Bean
    public HttpTransport httpTransport() {
        return new NetHttpTransport();
    }

    @Bean
    public GsonFactory gsonFactory() {
        return new GsonFactory();
    }

    @Bean
    public YouTube youtube(HttpTransport httpTransport, GsonFactory gsonFactory, HttpRequestInitializer httpRequestInitializer) {
        return new YouTube.Builder(httpTransport, gsonFactory, httpRequestInitializer).build();
    }
}
