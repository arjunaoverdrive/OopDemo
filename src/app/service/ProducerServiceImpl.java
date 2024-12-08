package app.service;

import app.domain.Producer;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

public class ProducerServiceImpl implements EntityService{

    private static final Map<Long, Producer> PRODUCERS = new HashMap<>();

    private static ProducerServiceImpl instance;

    private ProducerServiceImpl() {}

    public static ProducerServiceImpl getInstance() {
        if (instance == null) {
            instance = new ProducerServiceImpl();
        }
        return instance;
    }

    public Producer create(String name) {
        Producer producer = new Producer(name);
        PRODUCERS.put(producer.getId(), producer);
        return producer;
    }

    public Producer getById(Long producerId) {
        Producer producer = PRODUCERS.get(producerId);
        if (producer == null) {
            throw new RuntimeException(
                    MessageFormat.format("Producer with id {0} not found!", producerId)
            );
        }
        return producer;
    }

    @Override
    public Type getType() {
        return Type.PRODUCT;
    }

    public void printAll() {
        PRODUCERS.forEach((key, value) -> System.out.println(value));
    }
}
