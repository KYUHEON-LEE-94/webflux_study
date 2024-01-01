package com.study.webflux_study.webSocket;

import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

/**
 * packageName    : com.study.webflux_study.webSocket
 * fileName       : MyWebSocketHandler
 * author         : LEE KYUHEON
 * date           : 2024-01-01
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-01-01        LEE KYUHEON       최초 생성
 */
public class MyWebSocketHandler implements WebSocketHandler {

    @Override
    public Mono<Void> handle(WebSocketSession session) {
        // WebSocket 세션에서 발생하는 이벤트를 처리하는 로직을 작성합니다.
        // 예를 들어, 클라이언트로부터 메시지를 받아서 응답하는 코드 등이 들어갑니다.
        return session.send(
                Flux.interval(Duration.ofSeconds(2))
                        .map(i -> session.textMessage("Message #" + i))
        );
    }
}
