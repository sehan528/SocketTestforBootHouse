package org.example.eventexma;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
// ApplicationListener 인터페이스를 실제 구현하고 있는 클래스들을 Spring boot 가 가지고 있음.
// 사용자가 사전에 이를 구현해두면 ApplicationListener 는 특정시점에 구현된것을 실행한다.
public class StartupApplicationListener implements ApplicationListener <ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("애플리케이션 시작.");
        // Context 구현 이후 해당 클래스 (상속 받은 인터페이스 <ContextRefreshedEvent>) 를 실행하게 됩니다.
    }
}
