package org.example.transcationevent;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;
@Component
public class ItemRegistrationEventListener {
    @Async("customTaskExecutor")
    @TransactionalEventListener
    public void handleItemRegistrationEvent(ItemRegistrationEvent event) {
        System.out.println("Handling item registration event for item: " + event.getName() + ", price: " + event.getPrice());
// Item 등록 이벤트 처리 로직
    }
}