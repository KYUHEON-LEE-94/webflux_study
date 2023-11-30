package com.study.webflux_study.mongoDBConfig;

import com.study.webflux_study.mongoService.ServiceRepository;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * packageName    : com.study.webflux_study.mongoDBConfig
 * fileName       : ApplicationShutdownListener
 * author         : heon
 * date           : 2023-11-30
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-11-30           heon               최초 생성
 */
@Component
public class ApplicationShutdownListener {

    private final ServiceRepository serviceRepository;

    public ApplicationShutdownListener(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @EventListener(ContextClosedEvent.class)
    public void onApplicationEvent(ContextClosedEvent event) {
        // 어플리케이션이 종료될 때 실행할 코드 작성
        System.out.println("Application is shutting down. Performing cleanup...");

        serviceRepository.deleteAll();

        System.out.println("Cleanup completed.");
    }
}
