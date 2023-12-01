package com.study.webflux_study.eventDriven.vo;

/**
 * packageName    : com.study.webflux_study.eventDriven
 * fileName       : Event
 * author         : heon
 * date           : 2023-12-01
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-12-01           heon               최초 생성
 */
public abstract class Event {

    private long timestamp;

    public Event() {
        this.timestamp = System.currentTimeMillis();
    }

    public long getTimestamp() {
        return timestamp;
    }
}
