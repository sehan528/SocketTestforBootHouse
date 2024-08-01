// 2. 이벤트 발생 시 할 일 구현
package org.example.eventexma2;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class CustomEventListener {

    @EventListener
    public void handleCustomEvent(CustomEvent event) {
        System.out.println("handleCustomEvent Thread Name: " + Thread.currentThread().getName());
        System.out.println(event.getMessage());
    }
}
