package app.ui.actions.create;

import app.domain.Product;
import app.domain.Shop;
import app.service.ProductServiceImpl;
import app.service.ShopServiceImpl;
import app.ui.Action;

import java.util.Scanner;

public class CreateShopAction implements Action {

    private static final ShopServiceImpl SHOP_SERVICE = ShopServiceImpl.getInstance();

    public CreateShopAction() {
        super();
    }

    @Override
    public void execute() {
        System.out.println("Input name: ");

        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();

        System.out.println("Input address: ");
        String address = scanner.nextLine();


        Shop shop = SHOP_SERVICE.createShop(name, address);
        System.out.println(shop);
    }
}
