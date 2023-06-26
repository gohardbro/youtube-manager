package dev.gohard.youtubemanager.controller;

import com.google.api.services.youtube.model.Channel;
import com.google.api.services.youtube.model.Playlist;
import com.google.api.services.youtube.model.PlaylistItem;
import dev.gohard.youtubemanager.service.OAuthService;
import dev.gohard.youtubemanager.service.YoutubeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class YoutubeApiController {
    private final YoutubeServiceImpl youtubeService;
    private final OAuthService oAuthService;

    // 내가 직접만든 playlists 조회
    @GetMapping("/playlists")
    public String showPlaylists(Model model) {
        List<Playlist> playlists = youtubeService.getPlaylists(oAuthService.getAccessToken());
        model.addAttribute("playlists", playlists);
        return "playlists";
    }

    // 특정 플레이리스트의 영상들 조회
    @GetMapping("/playlists/{id}")
    public String showPlaylistItems(@PathVariable("id") String id, Model model) {
        List<PlaylistItem> playlistItems = youtubeService.getPlaylistItems(oAuthService.getAccessToken(), id);
        model.addAttribute("playlistItems", playlistItems);
        return "playlistItems";
    }

    // 좋아요한 영상들 조회
    @GetMapping("/playlists/likes")
    public String showlikedPlaylists(Model model) {
        List<Channel> channels = youtubeService.getChannels(oAuthService.getAccessToken());
        // getting liked videos playlist id
        String likedPlaylistId = channels.get(0).getContentDetails().getRelatedPlaylists().getLikes();
        // getting playlist items
        List<PlaylistItem> playlistItems = youtubeService.getPlaylistItems(oAuthService.getAccessToken(), likedPlaylistId);

        model.addAttribute("playlistItems", playlistItems);
        return "playlistItems";
    }

    // 채널정보 조회
    @GetMapping("/channels")
    public String showChannels(Model model) {
        List<Channel> channels = youtubeService.getChannels(oAuthService.getAccessToken());
        model.addAttribute("channels", channels);
        return "channels";
    }
}
