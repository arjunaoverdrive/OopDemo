package app.ui.builder;

import app.service.Type;
import app.ui.Menu;
import app.ui.MenuItem;
import app.ui.actions.create.CreatePriceAction;
import app.ui.actions.GetByIdAction;
import app.ui.ItemName;
import app.ui.actions.PrintAllAction;
import app.ui.actions.ReturnAction;

import java.util.ArrayList;
import java.util.List;

public class PriceMenuBuilder extends SubMenuBuilder {

    private static final Type TYPE = Type.PRICE;
    private static PriceMenuBuilder instance;
    private static final Menu MENU = new Menu("Price menu: ");

    private PriceMenuBuilder() {
    }

    public static PriceMenuBuilder getInstance() {
        if (instance == null) {
            instance = new PriceMenuBuilder();
        }
        return instance;
    }

    @Override
    public void buildMenu() {
        List<MenuItem> items = new ArrayList<>();
        MENU.setMenuItems(items);

        Menu nextMenu = RootMenuBuilder.getInstance().getRootMenu();

        MenuItem getById = createMenuItem(ItemName.GET_BY_ID, new GetByIdAction(TYPE), nextMenu);
        items.add(getById);

        MenuItem print = createMenuItem(ItemName.PRINT_ALL, new PrintAllAction(TYPE), nextMenu);
        items.add(print);

        MenuItem create = createMenuItem(ItemName.CREATE, new CreatePriceAction(), nextMenu);
        items.add(create);

        MenuItem returnItem = createMenuItem(ItemName.RETURN, new ReturnAction(), nextMenu);
        items.add(returnItem);
    }

    @Override
    public Menu getRootMenu() {
        return MENU;
    }
}
