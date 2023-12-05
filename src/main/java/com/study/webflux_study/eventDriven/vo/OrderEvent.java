package com.study.webflux_study.eventDriven.vo;

import lombok.Getter;

import java.io.Serializable;

/**
 * packageName    : com.study.webflux_study.eventDriven.vo
 * fileName       : OrderEvent
 * author         : heon
 * date           : 2023-12-05
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-12-05           heon               최초 생성
 */
@Getter
public class OrderEvent {
    private Long orderId;
    private String name;
}
