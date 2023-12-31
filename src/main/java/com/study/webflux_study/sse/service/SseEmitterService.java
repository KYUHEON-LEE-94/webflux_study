package com.study.webflux_study.sse.service;

import com.study.webflux_study.sse.dto.EventPayload;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * packageName    : com.study.webflux_study.sse.service
 * fileName       : SseEmitterService
 * author         : LEE KYUHEON
 * date           : 2023-12-31
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-12-31        LEE KYUHEON       최초 생성
 */
@Service
@Log4j2
public class SseEmitterService {
    // thread-safe 한 컬렉션 객체로 sse emitter 객체를 관리해야 한다.
    private final Map<String, SseEmitter> emitterMap = new ConcurrentHashMap<>();
    private static final long TIMEOUT = 60 * 1000;
    private static final long RECONNECTION_TIMEOUT = 1000L;

    //초기값 비교를 위한 증가
    private final AtomicInteger currentId = new AtomicInteger(0);

    public SseEmitter subscribe(String id) {
        log.debug("id: {}", id);
        SseEmitter emitter = createEmitter();
        //연결 세션 timeout 이벤트 핸들러 등록
        emitter.onTimeout(() -> {
            log.debug("server sent event timed out : id={}", id);
            //onCompletion 핸들러 호출
            emitter.complete();
        });

        //에러 핸들러 등록
        emitter.onError(e -> {
            log.debug("server sent event error occurred : id={}, message={}", id, e.getMessage());
            //onCompletion 핸들러 호출
            emitter.complete();
        });

        //SSE complete 핸들러 등록
        emitter.onCompletion(() -> {
            if (emitterMap.remove(id) != null) {
                log.debug("server sent event removed in emitter cache: id={}", id);
            }

            log.debug("disconnected by completed server sent event: id={}", id);
        });

        /**클라이언트 식별**/
        emitterMap.put(id, emitter);

        //초기 연결시에 응답 데이터를 전송할 수도 있다.
        try {
            SseEmitter.SseEventBuilder event = SseEmitter.event()
                    .name("event example")
                    //event id (id: id-1) - 재연결시 클라이언트에서 `Last-Event-ID` 헤더에 마지막 event id 를 설정
                    .id(String.valueOf(currentId.incrementAndGet()))
                    //event data payload (data: SSE connected)
                    .data("SSE connected")
                    //SSE 연결이 끊어진 경우 재접속 하기까지 대기 시간 (retry: <RECONNECTION_TIMEOUT>)
                    .reconnectTime(RECONNECTION_TIMEOUT);
            emitter.send(event);
        } catch (IOException e) {
            log.error("failure send media position data, id={}, {}", id, e.getMessage());
        }
        return emitter;
    }

    public void broadcast(EventPayload eventPayload) {
        emitterMap.forEach((id, emitter) -> {
            try {
                emitter.send(SseEmitter.event()
                        .name(eventPayload.memberName())
                        .id(eventPayload.memberId())
                        .reconnectTime(RECONNECTION_TIMEOUT)
                        .data(eventPayload, MediaType.APPLICATION_JSON));
                log.debug("sended notification, id={}, payload={}", id, eventPayload);
            } catch (IOException e) {
                //SSE 세션이 이미 해제된 경우
                log.error("fail to send emitter id={}, {}", id, e.getMessage());
            }
        });
    }

    /** Emitter 생성**/
    private SseEmitter createEmitter() {
        return new SseEmitter(TIMEOUT);
    }
}
