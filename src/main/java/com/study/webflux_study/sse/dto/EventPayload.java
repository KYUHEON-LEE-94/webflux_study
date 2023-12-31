package com.study.webflux_study.sse.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * packageName    : com.study.webflux_study.sse
 * fileName       : EventPayload
 * author         : LEE KYUHEON
 * date           : 2023-12-31
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-12-31        LEE KYUHEON       최초 생성
 */

public record EventPayload(@JsonProperty("memberId") String memberId,
                           @JsonProperty("memberName") String memberName,
                           @JsonProperty("memberAge") String memberAge) {
}