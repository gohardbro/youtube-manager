package dev.gohard.youtubemanager.controller;

import com.google.api.services.youtube.model.Channel;
import com.google.api.services.youtube.model.Playlist;
import com.google.api.services.youtube.model.PlaylistItem;
import dev.gohard.youtubemanager.service.OAuthService;
import dev.gohard.youtubemanager.service.SseEmitterService;
import dev.gohard.youtubemanager.service.YoutubeServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;


@Controller
@RequiredArgsConstructor
@Slf4j
public class YoutubeApiController {
    private final YoutubeServiceImpl youtubeService;
    private final OAuthService oAuthService;
    private final SseEmitterService sseEmitterService;

    // 내가 직접만든 playlists 조회
    @GetMapping("/playlists")
    public String showPlaylists(Model model) {
        List<Playlist> playlists = youtubeService.getPlaylists(oAuthService.getAccessToken());
        model.addAttribute("playlists", playlists);
        return "playlists";
    }

    // 특정 플레이리스트의 영상들 조회
    @GetMapping("/playlists/{id}")
    public String showPlaylistItems(@PathVariable("id") String id, @RequestParam String title, Model model) {
        List<PlaylistItem> playlistItems = youtubeService.getPlaylistItems(oAuthService.getAccessToken(), id);
        model.addAttribute("playlistItems", playlistItems);
        model.addAttribute("title", title);
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

    // playlist의 재생항목 삭제
    @DeleteMapping("/playlists/delete")
    public ResponseEntity<String> deletePlaylistItems(@RequestBody String[] selectedItemsIds, @RequestHeader HttpHeaders headers) throws IOException {
        log.info("받은데이터: {}", Arrays.toString(selectedItemsIds));

        // 선택된 여러개의 video들 순차적으로 삭제
        for (int i = 0; i < selectedItemsIds.length; i++) {
            youtubeService.deletePlaylistItems(oAuthService.getAccessToken(), selectedItemsIds[i]);

            // send progress msg
            List<String> sseToken = headers.get("sseToken");
            String uuid = null;
            if (sseToken != null) {
                uuid = sseToken.get(0);
            }
            sseEmitterService.sendMsg(uuid, i + 1 + " / " + selectedItemsIds.length);

        }
        return ResponseEntity.ok("삭제완료!");
    }
}
