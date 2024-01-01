package com.study.webflux_study.eventDriven.consumer;

import com.study.webflux_study.eventDriven.vo.OrderCanceledEvent;
import com.study.webflux_study.eventDriven.vo.OrderEvent;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

/**
 * packageName    : com.study.webflux_study.eventDriven.consumer
 * fileName       : EventSubscriber
 * author         : heon
 * date           : 2023-12-05
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-12-05           heon               최초 생성
 */
@Component
@Log4j2
public class EventSubscriber {

    private final StringRedisTemplate stringRedisTemplate;

    public EventSubscriber(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @RabbitListener(queues = "testQueue")
    public void handleOrderEvent(OrderEvent orderEvent) {
        log.info("Received order event: {}", orderEvent);

        final String key = "stirng_key";

        final ValueOperations<String, String> stirngStringValueOperations = stringRedisTemplate.opsForValue();
        stirngStringValueOperations.set(key, orderEvent.getName());
        final String result_1 = stirngStringValueOperations.get(key);
        log.info("result_1: {}", result_1);

    }
}
