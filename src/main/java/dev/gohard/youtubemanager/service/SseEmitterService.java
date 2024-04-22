package dev.gohard.youtubemanager.service;

import dev.gohard.youtubemanager.config.SseEmitters;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SseEmitterService {
    private final SseEmitters emitters;

    public SseEmitter subscribe(String uuid) throws IOException {
        return emitters.add(uuid);
    }

    public void sendMsg(String uuid, String msg) throws IOException {
        if (emitters.hasKey(uuid))
            emitters.get(uuid)
                    .send(SseEmitter.event().id(uuid).data(msg));
    }
}
