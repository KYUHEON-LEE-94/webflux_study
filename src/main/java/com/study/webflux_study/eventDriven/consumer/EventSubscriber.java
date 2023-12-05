package com.study.webflux_study.eventDriven.consumer;

import com.study.webflux_study.eventDriven.vo.OrderCanceledEvent;
import com.study.webflux_study.eventDriven.vo.OrderEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
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
@Slf4j
public class EventSubscriber {

    @RabbitListener(queues = "testQueue")
    public void handleOrderEvent(OrderEvent orderEvent) {
        // 이벤트 처리 로직 작성
        log.info("Received order event: {}", orderEvent.getOrderId());
    }
}
