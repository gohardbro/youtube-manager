package dev.gohard.youtubemanager.controller;

import com.google.api.services.youtube.model.Channel;
import com.google.api.services.youtube.model.Playlist;
import com.google.api.services.youtube.model.PlaylistItem;
import dev.gohard.youtubemanager.service.OAuthServiceImpl;
import dev.gohard.youtubemanager.service.YoutubeServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class YoutubeApiController {
    private final YoutubeServiceImpl youtubeService;
    private final OAuthServiceImpl oAuthServiceImpl;

    @GetMapping("/playlists")
    public String showPlaylists(Model model) {
        List<Playlist> playlists = youtubeService.getPlaylists(oAuthServiceImpl.getAccessToken());
        model.addAttribute("playlists", playlists);
        return "playlists";
    }

    @GetMapping("/playlists/likes")
    public String showlikedPlaylists(Model model) {
        List<Channel> channels = youtubeService.getChannels(oAuthServiceImpl.getAccessToken());
        // getting liked videos playlist id
        String likedPlaylistId = channels.get(0).getContentDetails().getRelatedPlaylists().getLikes();
        // getting playlist items
        List<PlaylistItem> playlistItems = youtubeService.getPlaylistItems(oAuthServiceImpl.getAccessToken(), likedPlaylistId);

        model.addAttribute("playlistItems", playlistItems);
        return "playlistItems";
    }

    @GetMapping("/channels")
    public String showChannels(Model model) {
        List<Channel> channels = youtubeService.getChannels(oAuthServiceImpl.getAccessToken());
        model.addAttribute("channels", channels);
        return "channels";
    }
}
