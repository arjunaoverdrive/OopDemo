package app;

import app.domain.Price;
import app.domain.Producer;
import app.domain.Product;
import app.domain.Shop;
import app.service.PriceServiceImpl;
import app.service.ProducerServiceImpl;
import app.service.ProductServiceImpl;
import app.service.ShopServiceImpl;

import java.math.BigDecimal;

public class ShopsManagement {

    private static ShopsManagement instance;

    private final ProductServiceImpl productService = ProductServiceImpl.getInstance();
    private final ProducerServiceImpl producerService = ProducerServiceImpl.getInstance();
    private final ShopServiceImpl shopService = ShopServiceImpl.getInstance();
    private final PriceServiceImpl priceService = PriceServiceImpl.getInstance();

    private ShopsManagement() {
    }

    public static ShopsManagement getInstance() {
        if (instance == null) {
            instance = new ShopsManagement();
        }
        return instance;
    }

    public Long createShop(String name, String address) {
        return shopService.createShop(name, address).getId();
    }

    public Shop findShopById(Long id) {
        return shopService.findShopById(id);
    }

    public void printAllShops() {
        shopService.printAllShops();
    }

    public Product createProduct(String name, Double weight, Long producerId) {
        Product product = productService.createProduct(name, weight, producerId);
        return product;
    }

    public void printAllProducts() {
        productService.printAllProducts();
    }

    public Producer createProducer(String name) {
        return producerService.createProducer(name);
    }

    public Price createPrice(BigDecimal price, Long productId, Long shopId) {
        return priceService.createPrice(price, productId, shopId);
    }

    public void printAllPrices() {
        priceService.printAllPrices();
    }
}
