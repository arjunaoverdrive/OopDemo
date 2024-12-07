package app.service;

import app.domain.Shop;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

public class ShopServiceImpl {

    private static final Map<Long, Shop> SHOPS = new HashMap<>();

    private static ShopServiceImpl instance;

    private ShopServiceImpl() {}

    public static ShopServiceImpl getInstance() {
        if (instance == null) {
            return new ShopServiceImpl();
        }
        return instance;
    }

    public Shop createShop(String name, String address) {
        Shop shop = new Shop(name, address);
        SHOPS.put(shop.getId(), shop);
        return shop;
    }

    public Shop findShopById(Long id) {
        Shop shop = SHOPS.get(id);
        if (shop == null) {
            throw new RuntimeException(
                    MessageFormat.format("Shop with id {0} not found!", id)
            );
        }
        return shop;
    }

    public void printAllShops() {
        SHOPS.forEach((key, value) -> System.out.println(value));
    }
}
