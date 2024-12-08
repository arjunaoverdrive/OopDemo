package app.ui;


import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Navigator {

    private Menu currentMenu;

    public Navigator(Menu currentMenu) {
        this.currentMenu = currentMenu;
    }

    public void printMenu() {
        System.out.println(this.currentMenu.getName());
        Map<Integer, MenuItem> menuItems = getMenuItems();
        menuItems.forEach((key, value) -> System.out.println((key + 1) + " " + value));
    }

    public void navigate(int index) {
        Map<Integer, MenuItem> itemsMap = getMenuItems();

        MenuItem menuItem = itemsMap.get(index - 1);
        menuItem.doAction();
        this.currentMenu = menuItem.getNextMenu();
    }

    private Map<Integer, MenuItem> getMenuItems() {
        List<MenuItem> menuItems = currentMenu.getMenuItems();
        Map<Integer, MenuItem> itemsMap = menuItems.stream()
                .collect(Collectors.toMap(
                        menuItems::indexOf, Function.identity()
                ));
        return itemsMap;
    }

}
