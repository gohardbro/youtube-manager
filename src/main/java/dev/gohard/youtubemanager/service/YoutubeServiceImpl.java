package dev.gohard.youtubemanager.service;

import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class YoutubeServiceImpl implements YoutubeService {
    private final YouTube youtube;

    @Override
    public List<Playlist> getPlaylists(String accessToken) {
        List<Playlist> playlists = new ArrayList<>();

        try {
            YouTube.Playlists.List request = youtube.playlists().list(Collections.singletonList("snippet"));
            request.setAccessToken(accessToken);
            request.setMine(true);
            request.setMaxResults(50L);

            PlaylistListResponse response = request.execute();
            playlists = response.getItems();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return playlists;
    }

    @Override
    public List<PlaylistItem> getPlaylistItems(String accessToken, String playlistId) {
        List<PlaylistItem> playlistItems = new ArrayList<>();

        String nextPageToken = null;

        try {
            do {
                YouTube.PlaylistItems.List request = youtube.playlistItems().list(Collections.singletonList("snippet"));
                request.setAccessToken(accessToken);
                request.setPlaylistId(playlistId);
                request.setMaxResults(50L); // 최대 50개 가능
                request.setPageToken(nextPageToken);

                PlaylistItemListResponse response = request.execute();
                playlistItems.addAll(response.getItems());

                nextPageToken = response.getNextPageToken();
            } while (nextPageToken != null);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return playlistItems;
    }

    public List<Channel> getChannels(String accessToken) {
        List<Channel> channels = new ArrayList<>();

        try {
            YouTube.Channels.List request = youtube.channels().list(Collections.singletonList("snippet,contentDetails"));
            request.setAccessToken(accessToken);
            request.setMine(true);

            ChannelListResponse response = request.execute();
            channels = response.getItems();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return channels;
    }

    @Override
    public void deletePlaylistItems(String accessToken, String playlistId) {
        try {
            YouTube.PlaylistItems.Delete request = youtube.playlistItems().delete(playlistId);
            request.setAccessToken(accessToken);
            request.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
