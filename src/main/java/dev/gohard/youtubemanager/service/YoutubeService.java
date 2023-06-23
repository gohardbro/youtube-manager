package dev.gohard.youtubemanager.service;

import com.google.api.services.youtube.model.Playlist;
import com.google.api.services.youtube.model.Video;
import dev.gohard.youtubemanager.util.YoutubeChannelResponse.YoutubeChannel;

import java.util.List;

public interface YoutubeService {
    List<Video> getLikedVideos(String accessToken);

    List<Playlist> getPlaylists(String accessToken);

    List<YoutubeChannel> getChannels(String accessToken);
}
