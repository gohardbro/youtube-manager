package dev.gohard.youtubemanager.controller;

import dev.gohard.youtubemanager.service.OAuthService;
import dev.gohard.youtubemanager.service.YoutubeApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class YoutubeApiController {
    private final YoutubeApiService youtubeService;
    private final OAuthService oAuthService;

    @GetMapping("/channels")
    public String showChannels(Model model) {
        ResponseEntity<String> responseEntity = youtubeService.getChannels(oAuthService.getAccessToken());

        model.addAttribute("channels", responseEntity.getBody());
        return "channels";
    }
}
