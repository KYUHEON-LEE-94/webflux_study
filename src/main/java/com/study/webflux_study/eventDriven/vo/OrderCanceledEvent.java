package com.study.webflux_study.eventDriven.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

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
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderCanceledEvent extends Event  {

    private Long orderId;
    private String name;
}
