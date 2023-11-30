package com.study.webflux_study.entitiy;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.redis.core.RedisHash;

/**
 * packageName    : com.study.webflux_study.entitiy
 * fileName       : AccountEntity
 * author         : heon
 * date           : 2023-11-15
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-11-15           heon               최초 생성
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@RedisHash("account")
public class RedisAccountEntity {

    @Id
    private String id;
    @Field(name = "name")
    private String name;
}
