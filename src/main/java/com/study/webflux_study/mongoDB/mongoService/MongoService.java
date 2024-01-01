package com.study.webflux_study.mongoDB.mongoService;

import com.study.webflux_study.entitiy.AccountEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;

/**
 * packageName    : com.study.webflux_study.mongoDB.mongoService
 * fileName       : MongoService
 * author         : LEE KYUHEON
 * date           : 2024-01-01
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-01-01        LEE KYUHEON       최초 생성
 */
@Service
public class MongoService {
    private final ServiceRepository serviceRepository;

    public MongoService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public Flux<AccountEntity> streamRecentAccounts() {
        return Flux.interval(Duration.ofSeconds(2)) //findAll()을 2초 간격으로 진행
                .flatMap(i -> serviceRepository.findAll());
    }

    public Flux<AccountEntity> streamOnce() {
        return Flux.defer(() -> serviceRepository.findAll())
                .repeatWhen(repeat -> repeat.delayElements(Duration.ofSeconds(2)))
                .log();
    }
}
