package com.study.webflux_study.eventDriven.service;

import com.study.webflux_study.eventDriven.vo.OrderCanceledEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * packageName    : com.study.webflux_study.eventDriven.service
 * fileName       : OrderService
 * author         : heon
 * date           : 2023-12-01
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-12-01           heon               최초 생성
 */
@Log4j2
@Service
@RequiredArgsConstructor
public class OrderService {
    private final ApplicationEventPublisher eventPublisher;

    public void cancel(Long orderId){
        log.info("{} 주문 취소 ",orderId);

        eventPublisher.publishEvent(new OrderCanceledEvent(orderId));
    }
}
