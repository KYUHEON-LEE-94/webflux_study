package com.study.webflux_study.testDB;

import com.study.webflux_study.entitiy.AccountEntity;
import com.study.webflux_study.service.ServiceRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
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
public class DataLoader implements ApplicationRunner {

    private final ServiceRepository serviceRepository;

    public DataLoader(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        // 초기 데이터 생성
            //mongoDB의 objectId 자동 생성
        AccountEntity example1 = new AccountEntity(null, "John Doe");
        AccountEntity example2 = new AccountEntity(null, "Jane Smith");

        // 데이터 삽입
        serviceRepository.saveAll(Flux.just(example1, example2)) //여러개를 한번에 저장
                .thenMany(serviceRepository.findAll()) //이전 작업이 완료되면 findAll() 실행
                .doOnNext(data -> System.out.println("Received data: " + data))
                .subscribe(System.out::println);
    }
}
