package app.ui.actions;

import app.service.EntityService;
import app.service.PriceServiceImpl;
import app.service.ProducerServiceImpl;
import app.service.ProductServiceImpl;
import app.service.ShopServiceImpl;
import app.service.Type;
import app.ui.Action;

import java.util.Map;
import java.util.Scanner;

public class GetByIdAction implements Action {

    private final Type type;
    private final Map<Type, EntityService> services = Map.of(
            Type.PRODUCER, ProducerServiceImpl.getInstance(),
            Type.PRODUCT, ProductServiceImpl.getInstance(),
            Type.SHOP, ShopServiceImpl.getInstance(),
            Type.PRICE, PriceServiceImpl.getInstance());

    public GetByIdAction(Type type) {
        this.type = type;
    }

    @Override
    public void execute() {
        EntityService entityService = services.get(type);

        Scanner scanner = new Scanner(System.in);
        Long id = null;
        while (id == null) {
            System.out.println("Input entity id");
            id = Long.parseLong(scanner.nextLine());
        }
        try {
            Object byId = entityService.getById(id);
            System.out.println(byId);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}
