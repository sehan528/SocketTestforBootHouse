// 1. 이벤트 정의
package org.example.eventexma;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class CustomEvent extends ApplicationEvent {
    private String message;

    public CustomEvent(Object source, String message) {
        super(source);
        this.message = message;
    }

}
