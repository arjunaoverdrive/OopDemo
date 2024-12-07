package app.service;

import app.domain.Price;
import app.domain.Product;
import app.domain.Shop;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

public class PriceServiceImpl {

    private final ShopServiceImpl shopService = ShopServiceImpl.getInstance();
    private final ProductServiceImpl productService = ProductServiceImpl.getInstance();

    private static final Map<Long, Price> PRICES = new HashMap<>();


    private static PriceServiceImpl instance;

    private PriceServiceImpl() {
    }

    public static PriceServiceImpl getInstance() {
        if (instance == null) {
            instance = new PriceServiceImpl();
        }
        return instance;
    }

    public Price createPrice(BigDecimal price, Long productId, Long shopId) {
        Price priceEntity = new Price(price);

        Product product = productService.getProductById(productId);
        product.addPrice(priceEntity);

        Shop shop = shopService.findShopById(shopId);
        shop.addPrice(priceEntity);

        PRICES.put(priceEntity.getId(), priceEntity);

        return priceEntity;
    }

    public Price getPriceById(Long priceId) {
        Price price = PRICES.get(priceId);
        if (price == null) {
            throw new RuntimeException(
                    MessageFormat.format("Price with id {0} not found!", priceId)
            );
        }
        return price;
    }

    public void printAllPrices() {
        PRICES.forEach((key, value) -> System.out.println(value));
    }
}