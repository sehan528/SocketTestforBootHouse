package org.example.transcationevent;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;
@Service
public class ItemService {
    private final ItemRepository itemRepository;
    private final ApplicationEventPublisher applicationEventPublisher;
    public ItemService(ItemRepository itemRepository, ApplicationEventPublisher applicationEventPublisher) {
        this.itemRepository = itemRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }
    @Transactional
    public void registerItem(String name, double price) {
// Item 등록 로직 (예: 데이터베이스에 Item 정보 저장)
        Item item = new Item(name, price);
        itemRepository.save(item);
        System.out.println("Registering item: " + name);
// 트랜잭션이 성공적으로 완료된 후 이벤트 게시
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
            @Override
            public void afterCommit() {
                applicationEventPublisher.publishEvent(new ItemRegistrationEvent(this, name, price));
            }
        });
// if(1 == 1){
// throw new RuntimeException("강제로 예외 발생");
// }
    }
}