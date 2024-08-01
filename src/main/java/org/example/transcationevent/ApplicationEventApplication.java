package org.example.transcationevent;

import org.example.eventexma2.CustomEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class ApplicationEventApplication implements CommandLineRunner{
    @Autowired
    private ItemService itemService;
    public static void main(String[] args) {
        SpringApplication.run(ApplicationEventApplication.class, args);
    }
    @Override
    public void run(String... args) throws Exception {
        itemService.registerItem("Sample Item", 29.99);
    }
}