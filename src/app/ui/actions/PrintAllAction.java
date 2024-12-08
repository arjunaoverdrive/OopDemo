package app.ui.actions;

import app.service.EntityService;
import app.service.PriceServiceImpl;
import app.service.ProducerServiceImpl;
import app.service.ProductServiceImpl;
import app.service.ShopServiceImpl;
import app.service.Type;
import app.ui.Action;

import java.util.Map;

public class PrintAllAction implements Action {

    private final Type type;
    private static final Map<Type, EntityService> SERVICES = Map.of(
            Type.PRODUCER, ProducerServiceImpl.getInstance(),
            Type.PRODUCT, ProductServiceImpl.getInstance(),
            Type.SHOP, ShopServiceImpl.getInstance(),
            Type.PRICE, PriceServiceImpl.getInstance()
    );

    public PrintAllAction(Type type) {
        this.type = type;
    }

    @Override
    public void execute() {
        System.out.println("Printing...");
        System.out.println("=".repeat(25));
        EntityService service = SERVICES.get(type);
        service.printAll();
        System.out.println("=".repeat(25));
    }
}
