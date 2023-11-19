package com.study.webflux_study;

import com.study.webflux_study.service.ServiceHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * packageName    : com.study.webflux_study
 * fileName       : CodeRouter
 * author         : heon
 * date           : 2023-11-15
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-11-15        heon       최초 생성
 */
@Configuration
public class CodeRouter {

    @Bean
    public RouterFunction<ServerResponse> routerFunction (ServiceHandler handler){
        return RouterFunctions.route(RequestPredicates.GET("/service/{id}"), handler::getService)
                .andRoute(RequestPredicates.GET("/service"), handler::getServices)
                .andRoute(RequestPredicates.POST("/service"), handler::createService)
                .andRoute(RequestPredicates.PUT("/service/{id}"), handler::updateService)
                .andRoute(RequestPredicates.DELETE("/service/{id}"), handler::deleteService);
    }
}
