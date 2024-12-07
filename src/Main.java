import app.ShopsManagement;
import app.domain.Price;
import app.domain.Producer;
import app.domain.Product;
import app.domain.Shop;

import java.math.BigDecimal;

public class Main {

    private static final ShopsManagement shopsManagement = ShopsManagement.getInstance();
    public static void main(String[] args) {

        Long shopId = shopsManagement.createShop("Spar", "Tula, Lenina ave, 27");
        Shop shop = shopsManagement.findShopById(shopId);
        System.out.println(shop);

        Producer kraft = shopsManagement.createProducer("Kraft");

        Product chocolate = shopsManagement.createProduct("Milka", 280., kraft.getId() );
        System.out.println(chocolate);

        Product coffee = shopsManagement.createProduct("Jackobs", 180., kraft.getId());

        Product coffee1 = shopsManagement.createProduct("Jackobs", 180., kraft.getId());
        System.out.println(coffee.equals(coffee1));
        shopsManagement.printAllProducts();

        Price coffeePrice = shopsManagement.createPrice(BigDecimal.valueOf(300.), coffee.getId(), shopId);

        System.out.println(coffeePrice);

        shopsManagement.printAllPrices();
    }
}