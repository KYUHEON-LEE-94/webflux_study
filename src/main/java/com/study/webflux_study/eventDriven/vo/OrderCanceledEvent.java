package com.study.webflux_study.eventDriven.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * packageName    : com.study.webflux_study.eventDriven
 * fileName       : OrderCanceledEvent
 * author         : heon
 * date           : 2023-12-01
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-12-01           heon               최초 생성
 */
@Getter
@AllArgsConstructor
public class OrderCanceledEvent extends Event{
    private Long orderId;
}
