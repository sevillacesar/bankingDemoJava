package ec.com.banking.demo.account.mov.services.impl;

import ec.com.banking.demo.account.mov.feign.ClienteFeignClient;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * @author cesarsevilla
 * bankingDemoJava
 */

@Service
public class KafkaConsumerService {
    private final ClienteFeignClient clienteFeignClient;

    public KafkaConsumerService(ClienteFeignClient clienteFeignClient) {
        this.clienteFeignClient = clienteFeignClient;
    }

    @KafkaListener(topics = "${client.kafka.topic}")
    public Long getDataByNameClient(String nameClient) {
        return Optional.ofNullable(this.clienteFeignClient.getDataByNameClient(nameClient))
                .orElseThrow(() -> new NoSuchElementException("No se encontro informacion del cliente: " + nameClient));
    }
}
