package dev.gohard.youtubemanager.service;

import com.google.api.services.youtube.model.Playlist;
import dev.gohard.youtubemanager.util.YoutubeChannelResponse.YoutubeChannel;
import dev.gohard.youtubemanager.util.YoutubePlaylistItemResponse;

import java.util.List;

public interface YoutubeService {
    List<YoutubePlaylistItemResponse.YoutubePlaylistItem> getLikedVideos(String accessToken);

    List<Playlist> getPlaylists(String accessToken);

    List<YoutubeChannel> getChannels(String accessToken);
}
