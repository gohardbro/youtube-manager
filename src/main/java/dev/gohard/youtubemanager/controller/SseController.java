package dev.gohard.youtubemanager.controller;

import dev.gohard.youtubemanager.service.SseEmitterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class SseController {
    private final SseEmitterService sseEmitterService;

    @GetMapping(value = "/sse/{uuid}", produces = "text/event-stream")
    public SseEmitter subscribe(@PathVariable String uuid) throws IOException {
        return this.sseEmitterService.subscribe(uuid);
    }
}
