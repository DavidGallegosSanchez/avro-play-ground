package com.gallegos.shop.producer;

import com.gallegos.shop.domain.generated.ShopOrder;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
@Slf4j
public class ShopOrderProducer {

    KafkaTemplate<String, ShopOrder> kafkaTemplate;

    public ShopOrderProducer(KafkaTemplate<String, ShopOrder> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(ShopOrder shopOrderAvro) {
        var producerRecord =
                new ProducerRecord<>("shop-orders", shopOrderAvro.getId().toString(), shopOrderAvro);

        ListenableFuture<SendResult<String,ShopOrder>> listenableFuture =  kafkaTemplate.send(producerRecord);
        listenableFuture.addCallback(new ListenableFutureCallback<>() {

            @Override
            public void onFailure(Throwable ex) {
                handleFailure(shopOrderAvro, ex);
            }

            @Override
            public void onSuccess(SendResult<String, ShopOrder> result) {
                handleSuccess(shopOrderAvro, result);
            }
        });
    }

    private void handleFailure(ShopOrder shopOrder, Throwable ex) {
        log.error("Error Sending the Message for {} and the exception is {}", shopOrder,ex.getMessage(), ex);
        try {
            throw ex;
        } catch (Throwable throwable) {
            log.error("Error in OnFailure: {}", throwable.getMessage());
        }


    }

    private void handleSuccess(ShopOrder coffeeOrder, SendResult<String, ShopOrder> result) {
        log.info("Message Sent SuccessFully for the key : {} and the value is {} , partition is {}",
                coffeeOrder.getId(), coffeeOrder, result.getRecordMetadata().partition());
    }
}
