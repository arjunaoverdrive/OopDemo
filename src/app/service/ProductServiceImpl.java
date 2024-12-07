package app.service;

import app.domain.Producer;
import app.domain.Product;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

public class ProductServiceImpl {

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

    public Product getProductById(Long id) {
        Product product = PRODUCTS.get(id);
        if (product == null) {
            throw new RuntimeException(
                    MessageFormat.format("Product with id {0} not found!", id)
            );
        }
        return product;
    }

    public Product createProduct(String name, Double weight, Long producerId) {
        Product product = new Product(name, weight);
        PRODUCTS.put(product.getId(), product);
        Producer producer = producerService.getProducerById(producerId);
        producer.addProduct(product);
        return product;
    }

    public void printAllProducts() {
        PRODUCTS.forEach((key, value) -> System.out.println(value));
    }

}
