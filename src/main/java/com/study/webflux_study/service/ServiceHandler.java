package com.study.webflux_study.service;

import com.study.webflux_study.entitiy.AccountEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;
import java.util.Optional;

/**
 * packageName    : com.study.webflux_study.service
 * fileName       : ServiceHandler
 * author         : heon
 * date           : 2023-11-15
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-11-15           heon               최초 생성
 */
@Service
public class ServiceHandler {
    private final ServiceRepository serviceRepository;
    @Autowired
    public ServiceHandler(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public Mono<ServerResponse> getService(ServerRequest request) {
        return serviceRepository.findById(request.pathVariable("id"))
                .flatMap(entity -> ServerResponse.ok().body(BodyInserters.fromValue(entity)))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> getServices(ServerRequest request){
        return ServerResponse.ok().body(serviceRepository.findAll(), AccountEntity.class);
        //반환되는 타입은 List 형식으로 반환되는게 맞음.
        // 다만, Spring WebFlux의 body 메서드는 기본적으로 ReactiveAdapterRegistry를 사용하여 리액티브 타입을 처리합니다.
        // List는 리액티브하지 않은(non-reactive) 타입이기 때문에 해당 타입을 그대로 사용할 수 있습니다.
    }

    public Mono<ServerResponse> createService(ServerRequest request) {
        return request.bodyToMono(AccountEntity.class)
                .flatMap(entity -> serviceRepository.save(entity))
                .flatMap(entity -> ServerResponse.created(URI.create("/service/" + entity.getId())).build());
    }

    public Mono<ServerResponse> updateService(ServerRequest request) {
        return request.bodyToMono(AccountEntity.class)
                .flatMap(service -> serviceRepository.findById(request.pathVariable("id"))
                        .flatMap(existingService -> {
                            existingService.setName(service.getName());
                            return serviceRepository.save(existingService);
                        }))
                .flatMap(service -> ServerResponse.noContent().build())
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> deleteService (ServerRequest request){
        return serviceRepository.deleteById(request.pathVariable("id"))
                .flatMap(result -> ServerResponse.noContent().build())
                .switchIfEmpty(ServerResponse.notFound().build());
    }
}
