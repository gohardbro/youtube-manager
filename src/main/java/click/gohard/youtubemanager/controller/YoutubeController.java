package click.gohard.youtubemanager.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class YoutubeController {

    @GetMapping("/videos")
    public String showLikedVideos() {return "myPlaylists";}

    @GetMapping("/myPlaylists")
    public String goMyPlaylists() {return "myPlaylists";}
}
