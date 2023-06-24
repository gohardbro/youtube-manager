package dev.gohard.youtubemanager.service;

import com.google.api.services.youtube.model.Channel;
import com.google.api.services.youtube.model.PlaylistItem;

import java.util.List;

public interface YoutubeService {
    List<PlaylistItem> getPlaylistItems(String accessToken, String playlistId);

    List<Channel> getChannels(String accessToken);
}
