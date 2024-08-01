package org.example.transcationevent;

import org.springframework.context.ApplicationEvent;

public class ItemRegistrationEvent extends ApplicationEvent {
    private final String name;
    private final double price;
    public ItemRegistrationEvent(Object source, String name, double price) {
        super(source);
        this.name = name;
        this.price = price;
    }
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
}