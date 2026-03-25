package org.fusif.game_detector;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@SpringBootApplication
@EnableScheduling
@Slf4j
public class ApplicationDetector {

    @Value("${app.home")
    private String home;

    public static void main(String[] args) {
        log.info("Starting application...");
        SpringApplication.run(ApplicationDetector.class, args);
    }

    @EventListener(ApplicationStartingEvent.class)
    public void ensureAppHomeExists() {
        try {
            Files.createDirectory(Path.of(home));
        } catch (IOException e) {
            log.error("Error creating app home directory", e);
        }
    }
}
