package app.ui.actions.create;

import app.domain.Producer;
import app.service.ProducerServiceImpl;
import app.ui.Action;

import java.util.Scanner;

public class CreateProducerAction implements Action {

    private static final ProducerServiceImpl PRODUCER_SERVICE = ProducerServiceImpl.getInstance();

    public CreateProducerAction() {
        super();
    }

    @Override
    public void execute() {
        System.out.println("Input name: ");

        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();

        Producer producer = PRODUCER_SERVICE.create(name);
        System.out.println(producer);
    }
}
