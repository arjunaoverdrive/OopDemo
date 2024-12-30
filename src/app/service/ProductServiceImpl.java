package app.service;

import app.domain.Producer;
import app.domain.Product;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ProductServiceImpl implements EntityService{

    private static final Map<Long, Product> PRODUCTS = new HashMap<>();

    private final ProducerServiceImpl producerService = ProducerServiceImpl.getInstance();

    private static ProductServiceImpl instance;

    private ProductServiceImpl() {}

    public static ProductServiceImpl getInstance() {
        if (instance == null) {
            instance = new ProductServiceImpl();
        }
        return instance;
    }

    private Long generateId() {
        if (PRODUCTS.isEmpty()) {
            return 1L;
        }
        long currentVal = PRODUCTS.keySet()
                .stream()
                .max(Long::compare)
                .get();
        return ++currentVal;
    }

    public Product getById(Long id) {
        Product product = PRODUCTS.get(id);
        if (product == null) {
            throw new RuntimeException(
                    MessageFormat.format("Product with id {0} not found!", id)
            );
        }
        return product;
    }

    public Product create(String name, Double weight, Long producerId) {
        Product product = new Product(generateId(), name, weight);
        PRODUCTS.put(product.getId(), product);
        Producer producer = producerService.getById(producerId);
        producer.addProduct(product);
        return product;
    }

    public void printAll() {
        PRODUCTS.forEach((key, value) -> System.out.println(value));
    }

    public List<Product> getAll() {
        return PRODUCTS.values().stream().toList();
    }

    public void addAll(Collection<Product> products) {
        Map<Long, Product> collect = products.stream()
                .collect(Collectors.toMap(Product::getId, Function.identity()));

        PRODUCTS.putAll(collect);
    }

    @Override
    public Type getType() {
        return Type.PRODUCT;
    }
}
