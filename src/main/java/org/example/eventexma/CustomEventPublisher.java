// 3. 이벤트 등록
package org.example.eventexma;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomEventPublisher {
    // Service 로직 역할을 합니다.
    private final ApplicationEventPublisher applicationEventPublisher;


    public void publisherEvent(final String message) {
        CustomEvent customEvent = new CustomEvent(this, message);
        applicationEventPublisher.publishEvent(customEvent);

    }

}
