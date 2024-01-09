package com.study.webflux_study.redis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * packageName    : com.study.webflux_study.redis.service
 * fileName       : ChaceRedisService
 * author         : heon
 * date           : 2024-01-09
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-01-09           heon               최초 생성
 */
@Service
public class CacheRedisService {

    StringRedisTemplate stringRedisTemplate;

    @Autowired
    public CacheRedisService(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    /**
     * value : 캐시의 이름
     * key: 캐시에 저장될 데이터의 키
     * **/
    @Cacheable(value = "myCache", key = "#key")
    public String getCachedValue(String key){
        /*매개변수로 받은 key를 데이터의 key값으로 사용*/

        return  "Cached Value for " + key;
    }
}
