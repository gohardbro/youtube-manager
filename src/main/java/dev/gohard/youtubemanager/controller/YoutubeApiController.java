package dev.gohard.youtubemanager.controller;

import dev.gohard.youtubemanager.service.YoutubeApiService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class YoutubeApiController {
    private final YoutubeApiService youtubeService;

    @GetMapping("/channels")
    public String showChannels(Model model, HttpSession session) {
        String accessToken = (String) session.getAttribute("accessToken");
        ResponseEntity<String> responseEntity = youtubeService.getChannels(accessToken);

        model.addAttribute("channels", responseEntity.getBody());
        return "channels";
    }
}
