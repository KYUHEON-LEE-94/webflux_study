package com.study.webflux_study.sse.controller;

import com.study.webflux_study.entitiy.AccountEntity;
import com.study.webflux_study.sse.dto.EventPayload;
import com.study.webflux_study.sse.service.SseEmitterService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import java.util.UUID;

/**
 * packageName    : com.study.webflux_study.sse
 * fileName       : SSERestController
 * author         : LEE KYUHEON
 * date           : 2023-12-31
 * description    : MVC 에서의 SSE 활용법
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-12-31        LEE KYUHEON       최초 생성
 */
@RestController
@Log4j2
public class SSERestController {

    private final SseEmitterService sseEmitterService;

    public SSERestController(SseEmitterService sseEmitterService) {
        this.sseEmitterService = sseEmitterService;
    }

    /**
     * sseEmitterService의 2초 스케줄링을 통해 show
     * **/
//    @GetMapping(path = "/v1/sse", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
//    public ResponseEntity<SseEmitter> showAll() {
//        SseEmitter emitter = sseEmitterService.getAllFromMongo();
//        return ResponseEntity.ok(emitter);
//    }

    /**
     * 응답 mime type 은 반드시 text/event-stream 이여야 한다.
     * 클라이언트로 부터 SSE subscription 을 수락한다.
     * **/
    @GetMapping(path = "/v1/sse/subscribe", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<SseEmitter> subscribe() {
        String sseId = UUID.randomUUID().toString();
        SseEmitter emitter = sseEmitterService.subscribe(sseId);
        return ResponseEntity.ok(emitter);
    }


    @GetMapping(path = "/v1/sse/subscribe/{id}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<SseEmitter> subscribeById(@PathVariable String id) {
        SseEmitter emitter = sseEmitterService.subscribe(id);
        return ResponseEntity.ok(emitter);
    }

    /**
     * eventPayload 를 SSE 로 연결된 모든 클라이언트에게 broadcasting 한다.
     * **/
    @PostMapping(path = "/v1/sse/broadcast")
    public ResponseEntity<Void> broadcast(@RequestBody EventPayload eventPayload) {
        sseEmitterService.broadcast(eventPayload);
        return ResponseEntity.ok().build();
    }
}
