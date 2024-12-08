package app.ui.actions.create;

import app.domain.Price;
import app.service.PriceServiceImpl;
import app.ui.Action;

import java.math.BigDecimal;
import java.util.Scanner;

public class CreatePriceAction implements Action {

    private static final PriceServiceImpl PRICE_SERVICE = PriceServiceImpl.getInstance();

    public CreatePriceAction() {
        super();
    }

    @Override
    public void execute() {
        System.out.println("Input price: ");

        Scanner scanner = new Scanner(System.in);
        BigDecimal price = BigDecimal.valueOf(scanner.nextDouble());



        System.out.println("Input shop id: ");
        Long shopId = scanner.nextLong();

        System.out.println("Input product id: ");
        Long productId = scanner.nextLong();

        Price priceEntity = PRICE_SERVICE.createPrice(price, productId, shopId );
        System.out.println(priceEntity);
    }
}
