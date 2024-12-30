package app.service;

import app.domain.Producer;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    private Long generateId() {
        if (PRODUCERS.isEmpty()) {
            return 1L;
        }
        long currentValue = PRODUCERS.keySet()
                .stream()
                .max(Long::compare)
                .get();
        return ++currentValue;
    }
    public Producer create(String name) {
        Producer producer = new Producer(generateId(), name);
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

    public List<Producer> getAll() {
        return PRODUCERS.values().stream().toList();
    }

    public void addAll(Collection<Producer> producers) {
        Map<Long, Producer> collect = producers.stream().collect(Collectors.toMap(Producer::getId, Function.identity()));
        PRODUCERS.putAll(collect);
    }

    @Override
    public Type getType() {
        return Type.PRODUCT;
    }

    public void printAll() {
        PRODUCERS.forEach((key, value) -> System.out.println(value));
    }
}
