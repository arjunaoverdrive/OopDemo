package app.service;

import app.domain.Shop;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ShopServiceImpl implements EntityService {

    private static final Map<Long, Shop> SHOPS = new HashMap<>();

    private static ShopServiceImpl instance;

    private ShopServiceImpl() {
    }

    public static ShopServiceImpl getInstance() {
        if (instance == null) {
            return new ShopServiceImpl();
        }
        return instance;
    }

    private Long generateId() {
        if (SHOPS.isEmpty()) {
            return 1L;
        }
        long current =  SHOPS.keySet()
                .stream()
                .max(Comparator.comparing(Long::longValue))
                .get();
        return ++current;
    }

    public Shop createShop(String name, String address) {
        Shop shop = new Shop(generateId(), name, address);
        SHOPS.put(shop.getId(), shop);
        return shop;
    }

    public Shop getById(Long id) {
        Shop shop = SHOPS.get(id);
        if (shop == null) {
            throw new RuntimeException(
                    MessageFormat.format("Shop with id {0} not found!", id)
            );
        }
        return shop;
    }

    public List<Shop> getAll() {
        return SHOPS.values().stream().toList();
    }

    @Override
    public Type getType() {
        return Type.SHOP;
    }

    public void printAll() {
        SHOPS.forEach((key, value) -> System.out.println(value));
    }

    public void addAll(Collection<Shop> shops) {
        Map<Long, Shop> collect = shops.stream().collect(Collectors.toMap(Shop::getId, Function.identity()));
        SHOPS.putAll(collect);
    }
}
