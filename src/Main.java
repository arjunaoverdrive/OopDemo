import app.Price;
import app.Producer;
import app.Product;
import app.Shop;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Shop shop = new Shop("Spar", "Tula, Lenina ave, 27");
        System.out.println(shop);

        Product chocolate = new Product();
        chocolate.setName("Milka" );
        chocolate.setWeight(300);
//        System.out.println(chocolate);


        Producer kraft = new Producer("Kraft");

        Product coffee = new Product("Jakobs", 180);

        coffee.setProducer(kraft);
        List<Product> products = new ArrayList<>(List.of(coffee, chocolate));
        for(Product p: products){
            System.out.println(p);
        }

        Product coffee1 = new Product("Jackobs", 180);
        System.out.println(coffee.equals(coffee1));

        Price coffeePrice = new Price(BigDecimal.valueOf(300.));
        coffee.addPrice(coffeePrice);
        shop.addPrice(coffeePrice);


        System.out.println(coffeePrice);
//        Collections.sort(products);

        for(Product p: products){
            System.out.println(p);
        }
    }
}