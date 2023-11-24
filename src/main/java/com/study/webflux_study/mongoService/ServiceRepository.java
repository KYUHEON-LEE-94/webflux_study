package com.study.webflux_study.mongoService;

import com.study.webflux_study.entitiy.AccountEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * packageName    : com.study.webflux_study.service
 * fileName       : ServiceRepository
 * author         : heon
 * date           : 2023-11-15
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-11-15           heon               최초 생성
 */
@Repository
public interface ServiceRepository extends ReactiveMongoRepository<AccountEntity, String> {

    //JpaRepository는 Optional<Entity>를 return한다.
    //그러나, Reactive 환경에서는 Mono, Flux를 return해야한다. 고로 ReactiveCrudRepository를 사용한다.
}
