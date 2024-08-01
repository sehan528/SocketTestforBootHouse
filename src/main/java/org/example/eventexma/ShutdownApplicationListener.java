package org.example.eventexma;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

@Component
public class ShutdownApplicationListener implements ApplicationListener<ContextClosedEvent> {
    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        System.out.println("어플리케이션 종료");
        // 실행 도중엔 안 잡힙니다. 종료 되게 되면 해당 클래스가 실행됩니다.
        // 이후 주석 밑으로 메서드들을 넣으면 해당 조건에 맞는 이벤트들이 작동하게 됩니다.
    }
}
