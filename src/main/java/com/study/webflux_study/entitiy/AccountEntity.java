package com.study.webflux_study.entitiy;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

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
@Data
@Entity
@Getter
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @Column
    private String name;
}
