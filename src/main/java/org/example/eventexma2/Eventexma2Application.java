package org.example.eventexma2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Eventexma2Application implements CommandLineRunner {

    @Autowired
    private CustomEventPublisher customEventPublisher;

    public static void main(String[] args) {
        SpringApplication.run(Eventexma2Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        customEventPublisher.publisherEvent("Custom Event test");
        System.out.println("Eventexma2Application Thread Name: " + Thread.currentThread().getName());
    }

}
