package com.study.webflux_study.redis.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * packageName    : com.study.webflux_study.redis.service
 * fileName       : CacheRedisServiceTest
 * author         : heon
 * date           : 2024-01-09
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-01-09           heon               최초 생성
 */
@SpringBootTest
class CacheRedisServiceTest {
    private static final Logger log = LogManager.getLogger(CacheRedisServiceTest.class);

    @Autowired
    CacheRedisService cacheRedisService;

    @Test
    @DisplayName("캐시 서비스 테스트")
    void getCache(){
        var value = cacheRedisService.getCachedValue("test_key");
        log.info("value: {}", value);
    }
}