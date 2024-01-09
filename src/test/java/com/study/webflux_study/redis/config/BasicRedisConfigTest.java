package com.study.webflux_study.redis.config;

import com.study.webflux_study.redis.service.CacheRedisService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * packageName    : com.study.webflux_study.redis.config
 * fileName       : RedisConfigTest
 * author         : heon
 * date           : 2023-11-24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-11-24           heon               최초 생성
 */
@SpringBootTest
class BasicRedisConfigTest {
    private static final Logger log = LogManager.getLogger(BasicRedisConfigTest.class);

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Test
    @DisplayName("opsForValue() 메소드를 사용해서 레디스 string 타입의 자료구조에 접근")
    void testStrings(){
        final String key = "string_key";

        final ValueOperations<String, String> stirngStringValueOperations = stringRedisTemplate.opsForValue();

        stirngStringValueOperations.set(key, "1");
        final String result_1 = stirngStringValueOperations.get(key);
        log.info("result_1: {}", result_1);

        stirngStringValueOperations.increment(key);
        final String result_2 = stirngStringValueOperations.get(key);
        log.info("result_2: {}", result_2);

    }

    @Test
    @DisplayName("opsForList() 메소드를 사용해서 레디스 list 타입의 자료구조에 접근")
    public void testList() {
        final String key = "newkey";

        final ListOperations<String, String> stringStringListOperations = stringRedisTemplate.opsForList();

        stringStringListOperations.rightPush(key, "H");
        stringStringListOperations.rightPush(key, "e");
        stringStringListOperations.rightPush(key, "l");
        stringStringListOperations.rightPush(key, "l");
        stringStringListOperations.rightPush(key, "o");

        stringStringListOperations.rightPushAll(key, " ", "a", "a", "b", "b");

        final String character_1 = stringStringListOperations.index(key, 1);

        log.info("character_1 = {}", character_1);

        final Long size = stringStringListOperations.size(key);

        log.info("size = {}", + size);

        final List<String> ResultRange = stringStringListOperations.range(key, 0, 9);

        log.info("ResultRange = {}", Arrays.toString(ResultRange.toArray()));
    }

    @Test
    public void testSet() {
        String key = "sabarada";
        SetOperations<String, String> stringStringSetOperations = stringRedisTemplate.opsForSet();

        stringStringSetOperations.add(key, "H");
        stringStringSetOperations.add(key, "e");
        stringStringSetOperations.add(key, "l");
        stringStringSetOperations.add(key, "l");
        stringStringSetOperations.add(key, "o");

        Set<String> sabarada = stringStringSetOperations.members(key);

        log.info("members = {}", Arrays.toString(sabarada.toArray()));

        Long size = stringStringSetOperations.size(key);

        log.info("size = {}", size);

        Cursor<String> cursor = stringStringSetOperations.scan(key, ScanOptions.scanOptions().match("*").count(3).build());

        while(cursor.hasNext()) {
            log.info("cursor = {}", cursor.next());
        }
    }

}