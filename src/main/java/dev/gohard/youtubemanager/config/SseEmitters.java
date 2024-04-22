package dev.gohard.youtubemanager.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SseEmitters {
    private static final long TIMEOUT = 60L * 1000;
    private final ConcurrentHashMap<String, SseEmitter> emitters = new ConcurrentHashMap<>();

    public SseEmitter add(String uuid) throws IOException {
        SseEmitter emitter = new SseEmitter(TIMEOUT);
        emitters.put(uuid, emitter);

        // emitter deletion logic
        emitter.onCompletion(() -> this.emitters.remove(uuid));
        emitter.onTimeout(() -> this.emitters.remove(uuid));
        emitter.onError((callback) -> this.emitters.remove(uuid));

        // sending connection response msg
        emitter.send(SseEmitter.event().id(uuid).data("sse connected!"));

        return emitter;
    }

    public boolean hasKey(String uuid) {
        return this.emitters.containsKey(uuid);
    }

    public SseEmitter get(String uuid) {
        return this.emitters.get(uuid);
    }
}
