package app.service;

import app.domain.Producer;
import app.domain.Product;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

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
        Product product = new Product(name, weight);
        PRODUCTS.put(product.getId(), product);
        Producer producer = producerService.getById(producerId);
        producer.addProduct(product);
        return product;
    }

    public void printAll() {
        PRODUCTS.forEach((key, value) -> System.out.println(value));
    }

    @Override
    public Type getType() {
        return Type.PRODUCT;
    }
}
