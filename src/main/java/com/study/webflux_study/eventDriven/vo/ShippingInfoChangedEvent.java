package com.study.webflux_study.eventDriven.vo;

/**
 * packageName    : com.study.webflux_study.eventDriven
 * fileName       : ShippingInfoChangedEvent
 * author         : heon
 * date           : 2023-12-01
 * description    : 배송지 변경에 발생하는 이벤트가 가지는 필드
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-12-01           heon               최초 생성
 */
public class ShippingInfoChangedEvent {
    private int number;
    private String newShippingInfo;
    private long timestamp;
}
