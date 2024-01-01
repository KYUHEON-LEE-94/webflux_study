package com.study.webflux_study.mongoDB.controller;

import com.study.webflux_study.entitiy.AccountEntity;
import com.study.webflux_study.mongoDB.mongoService.MongoService;
import com.study.webflux_study.mongoDB.mongoService.ServiceHandler;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;

/**
 * packageName    : com.study.webflux_study.mongoDB.controller
 * fileName       : MongoRestController
 * author         : LEE KYUHEON
 * date           : 2024-01-01
 * description    : WebFlux에서 SSE 사용
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-01-01        LEE KYUHEON       최초 생성
 */
@RestController
public class MongoRestController {

    private final ServiceHandler handler;
    private final MongoService service;

    @Autowired
    public MongoRestController(ServiceHandler handler, MongoService service) {
        this.handler = handler;
        this.service = service;
    }

    @GetMapping("/hello")
    public ResponseEntity <Flux<AccountEntity>> sayHello(HttpServletRequest request) {
        return ResponseEntity.ok().body(handler.getAll()) ;
    }



    /**
     * reactive 프로그래밍을 이용해 2초간격 전송
     * **/
//    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
//    public Flux<AccountEntity> streamAccounts() {
//        return service.streamRecentAccounts()
//                .delayElements(Duration.ofSeconds(2)); // 2초 간격으로 전송
//    }

    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE) //SSE 방식
    public Flux<AccountEntity> streamAccounts() {
        return service.streamRecentAccounts();
    }

    @GetMapping(value = "/streamOnce", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<AccountEntity> streamOnce() {
        return service.streamOnce()
                .delayElements(Duration.ofSeconds(1)); // 받아온 객체의 elments를 1초간격으로 전송
    }

}
