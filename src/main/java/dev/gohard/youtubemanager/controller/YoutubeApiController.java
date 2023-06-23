package dev.gohard.youtubemanager.controller;

import dev.gohard.youtubemanager.service.OAuthServiceImpl;
import dev.gohard.youtubemanager.service.YoutubeServiceImpl;
import dev.gohard.youtubemanager.util.YoutubeChannelResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class YoutubeApiController {
    private final YoutubeServiceImpl youtubeService;
    private final OAuthServiceImpl oAuthServiceImpl;

    @GetMapping("/channels")
    public String showChannels(Model model) {
//        ResponseEntity<String> responseEntity = youtubeService.getChannels(oAuthServiceImpl.getAccessToken());
//        model.addAttribute("channels", responseEntity.getBody());

        List<YoutubeChannelResponse.YoutubeChannel> channels = youtubeService.getChannels(oAuthServiceImpl.getAccessToken());
        model.addAttribute("channels", channels);
        return "channels";
    }
}
