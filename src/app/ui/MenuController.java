package app.ui;

import app.ui.builder.RootMenuBuilder;

import java.util.Scanner;

public class MenuController {

    private static MenuController instance;

    private MenuController () {}

    public static MenuController getInstance() {
        if (instance == null) {
            instance = new MenuController();
        }
        return instance;
    }

    private final Builder builder = RootMenuBuilder.getInstance();
    private final Navigator navigator = new Navigator(builder.getRootMenu());

    public void run() {

        this.builder.buildMenu();
        String prompt = "Input action number ";

        while (true) {
            this.navigator.printMenu();
            System.out.println(prompt);
            Scanner scanner = new Scanner(System.in);
            String itemIndex = scanner.next().trim();

            while (!itemIndex.matches("\\d+")) {
                System.out.println(prompt);
                itemIndex = scanner.nextLine().trim();
            }

            this.navigator.navigate(Integer.parseInt(itemIndex));
        }
    }

}
