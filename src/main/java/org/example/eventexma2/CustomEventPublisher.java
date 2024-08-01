// 3. 이벤트 등록
package org.example.eventexma2;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomEventPublisher {
    // Service 로직 역할을 합니다.
    private final ApplicationEventPublisher applicationEventPublisher;


    public void publisherEvent(final String message) {
        System.out.println("publisherEvent Thread Name: " + Thread.currentThread().getName());
        CustomEvent customEvent = new CustomEvent(this, message);
        applicationEventPublisher.publishEvent(customEvent);

    }

}
