package com.study.webflux_study.mongoDBConfig;

import com.study.webflux_study.entitiy.AccountEntity;
import com.study.webflux_study.eventDriven.consumer.EventSubscriber;
import com.study.webflux_study.eventDriven.vo.OrderCanceledEvent;
import com.study.webflux_study.eventDriven.vo.OrderEvent;
import com.study.webflux_study.mongoService.ServiceRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

/**
 * packageName    : com.study.webflux_study.testDB
 * fileName       : DataLoader
 * author         : heon
 * date           : 2023-11-22
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-11-22           heon               최초 생성
 */
@Component
@Log4j2
public class DataLoader implements ApplicationRunner {

    private final ServiceRepository serviceRepository;
    private final EventSubscriber eventSubscriber;

    public DataLoader(ServiceRepository serviceRepository, EventSubscriber eventSubscriber) {
        this.eventSubscriber = eventSubscriber;
        this.serviceRepository = serviceRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        // 초기 데이터 생성
        //mongoDB의 objectId 자동 생성
        Flux<AccountEntity> all = serviceRepository.findAll();

        all.count().subscribe(count -> {
            if (count < 4) {
                // DB에 저장된 데이터가 5개 미만일 때만 실행
                AccountEntity example1 = new AccountEntity(null, "John Doe");
                AccountEntity example2 = new AccountEntity(null, "Jane Smith");

                // 데이터 삽입
                serviceRepository.saveAll(Flux.just(example1, example2))
                        .thenMany(serviceRepository.findAll())
                        .doOnNext(data -> System.out.println("Received data: " + data))
                        .subscribe(item -> log.info("item: {}",item));
            }
        });
    }
}
