package app.service;

import app.domain.Price;
import app.domain.Product;
import app.domain.Shop;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PriceServiceImpl implements EntityService{

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

    private Long generateId() {
        if(PRICES.isEmpty()) {
            return 1L;
        }
        long currentVal = PRICES.keySet()
                .stream()
                .max(Long::compare)
                .get();
        return ++currentVal;
    }

    public Price createPrice(BigDecimal price, Long productId, Long shopId) {
        Price priceEntity = new Price(generateId(), price);

        Product product = productService.getById(productId);
        product.addPrice(priceEntity);

        Shop shop = shopService.getById(shopId);
        shop.addPrice(priceEntity);

        PRICES.put(priceEntity.getId(), priceEntity);

        return priceEntity;
    }

    public Price getById(Long priceId) {
        Price price = PRICES.get(priceId);
        if (price == null) {
            throw new RuntimeException(
                    MessageFormat.format("Price with id {0} not found!", priceId)
            );
        }
        return price;
    }

    public List<Price> getAll() {
        return PRICES.values().stream().toList();
    }

    @Override
    public Type getType() {
        return Type.PRICE;
    }

    public void printAll() {
        PRICES.forEach((key, value) -> System.out.println(value));
    }

    public void addAll(Set<Price> prices) {
        Map<Long, Price> collect = prices.stream().collect(Collectors.toMap(Price::getId, Function.identity()));
        PRICES.putAll(collect);
    }
}
