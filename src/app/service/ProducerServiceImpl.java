package app.service;

import app.domain.Producer;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

public class ProducerServiceImpl {

    private static final Map<Long, Producer> PRODUCERS = new HashMap<>();

    private static ProducerServiceImpl instance;

    private ProducerServiceImpl() {}

    public static ProducerServiceImpl getInstance() {
        if (instance == null) {
            instance = new ProducerServiceImpl();
        }
        return instance;
    }

    public Producer createProducer(String name) {
        Producer producer = new Producer(name);
        PRODUCERS.put(producer.getId(), producer);
        return producer;
    }

    public Producer getProducerById(Long producerId) {
        Producer producer = PRODUCERS.get(producerId);
        if (producer == null) {
            throw new RuntimeException(
                    MessageFormat.format("Producer with id {0} not found!", producerId)
            );
        }
        return producer;
    }

    public void printAllProducers() {
        PRODUCERS.forEach((key, value) -> System.out.println(value));
    }
}
