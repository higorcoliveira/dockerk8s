package br.com.higorcoliveira.dockerk8s.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.availability.ApplicationAvailability;
import org.springframework.boot.availability.AvailabilityChangeEvent;
import org.springframework.boot.availability.ReadinessState;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReadinessController {

    private static Logger logger = LoggerFactory.getLogger(ReadinessController.class);

    private final ApplicationAvailability availability;

    private final ApplicationEventPublisher eventPublisher;

    public ReadinessController(ApplicationAvailability availability, ApplicationEventPublisher eventPublisher) {
        this.availability = availability;
        this.eventPublisher = eventPublisher;
    }

    @PostMapping("/change-readiness")
    public void changeReadiness() {
        var state = availability.getReadinessState().equals(ReadinessState.ACCEPTING_TRAFFIC)
                ? ReadinessState.REFUSING_TRAFFIC
                : ReadinessState.ACCEPTING_TRAFFIC;
        AvailabilityChangeEvent.publish(eventPublisher, this, state);

        logger.info("Changed readiness to " + state);
    }
}
