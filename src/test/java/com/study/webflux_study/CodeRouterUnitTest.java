package com.study.webflux_study;

import com.study.webflux_study.entitiy.AccountEntity;
import com.study.webflux_study.mongoDB.controller.CodeRouter;
import com.study.webflux_study.mongoDB.mongoService.ServiceHandler;
import com.study.webflux_study.mongoDB.mongoService.ServiceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.BDDMockito.given;

/**
 * packageName    : com.study.webflux_study
 * fileName       : CodeRouterUnitTest
 * author         : LEE KYUHEON
 * date           : 2023-11-19
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-11-19        LEE KYUHEON       최초 생성
 */
@ExtendWith(MockitoExtension.class)
@WebFluxTest(CodeRouter.class)
@Import(ServiceHandler.class)
class CodeRouterUnitTest {

    @MockBean
    ServiceRepository serviceRepository;

    private final WebTestClient webTestClient;

    public CodeRouterUnitTest() {
        this.webTestClient = WebTestClient.bindToController(new CodeRouter()).build();
    }

    @Test
    void testGetService(){

        AccountEntity account = new AccountEntity();
        account.setId("1");
        account.setName("이름");
        given(this.serviceRepository.findById("1")).willReturn(Mono.just(account));

        webTestClient.get()
                .uri("/service/" + "1").accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isNotFound(); //객체가 없는 경우는 Not Found return하게 해놓음
    }

}