package app.ui.actions.create;

import app.domain.Product;
import app.service.ProductServiceImpl;
import app.ui.Action;

import java.util.Scanner;

public class CreateProductAction implements Action {
    private static final ProductServiceImpl PRODUCT_SERVICE = ProductServiceImpl.getInstance();

    public CreateProductAction() {
        super();
    }

    @Override
    public void execute() {
        System.out.println("Input name: ");

        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();

        System.out.println("Input weight: ");
        Double weight = scanner.nextDouble();

        System.out.println("Input provider id: ");
        Long providerId = scanner.nextLong();

        Product product = PRODUCT_SERVICE.create(name, weight, providerId);
        System.out.println(product);
    }
}
