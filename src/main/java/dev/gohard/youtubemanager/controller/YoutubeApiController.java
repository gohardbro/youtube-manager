package dev.gohard.youtubemanager.controller;

import dev.gohard.youtubemanager.service.OAuthServiceImpl;
import dev.gohard.youtubemanager.service.YoutubeServiceImpl;
import dev.gohard.youtubemanager.util.YoutubePlaylistItemResponse.YoutubePlaylistItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

import static dev.gohard.youtubemanager.util.YoutubeChannelResponse.*;

@Controller
@RequiredArgsConstructor
public class YoutubeApiController {
    private final YoutubeServiceImpl youtubeService;
    private final OAuthServiceImpl oAuthServiceImpl;

    @GetMapping("/channels")
    public String showChannels(Model model) {
        List<YoutubeChannel> channels = youtubeService.getChannels(oAuthServiceImpl.getAccessToken());
        model.addAttribute("channels", channels);
        return "channels";
    }

    @GetMapping("/likes")
    public String showLikes(Model model) {
        List<YoutubePlaylistItem> likedVideos = youtubeService.getLikedVideos(oAuthServiceImpl.getAccessToken());
        model.addAttribute("likes", likedVideos);
        return "likes";
    }
}
