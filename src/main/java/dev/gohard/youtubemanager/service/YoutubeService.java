package dev.gohard.youtubemanager.service;

import com.google.api.services.youtube.model.Channel;
import com.google.api.services.youtube.model.Playlist;
import com.google.api.services.youtube.model.PlaylistItem;

import java.util.List;

public interface YoutubeService {
    List<Playlist> getPlaylists(String accessToken);
    List<PlaylistItem> getPlaylistItems(String accessToken, String playlistId);
    List<Channel> getChannels(String accessToken);
    void deletePlaylistItems(String accessToken, String playlistId);
}
