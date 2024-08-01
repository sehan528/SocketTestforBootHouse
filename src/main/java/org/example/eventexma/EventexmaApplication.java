package org.example.eventexma;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EventexmaApplication implements CommandLineRunner {

    @Autowired
    private CustomEventPublisher customEventPublisher;

    public static void main(String[] args) {
        SpringApplication.run(EventexmaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        customEventPublisher.publisherEvent("Custom Event test");
    }

}
