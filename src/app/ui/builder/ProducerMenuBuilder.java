package app.ui.builder;

import app.service.Type;
import app.ui.Builder;
import app.ui.Menu;
import app.ui.MenuItem;
import app.ui.actions.GetByIdAction;
import app.ui.actions.ItemName;
import app.ui.actions.PrintAllAction;
import app.ui.actions.ReturnAction;
import app.ui.actions.create.CreateProducerAction;

import java.util.ArrayList;
import java.util.List;

public class ProducerMenuBuilder extends SubMenuBuilder {

    private static final Type TYPE = Type.PRODUCER;
    private static ProducerMenuBuilder instance;
    private static final Menu MENU = new Menu("Producer menu: ");

    private ProducerMenuBuilder() {
    }

    public static ProducerMenuBuilder getInstance() {
        if (instance == null) {
            instance = new ProducerMenuBuilder();
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

        MenuItem create = createMenuItem(ItemName.CREATE, new CreateProducerAction(), nextMenu);
        items.add(create);

        MenuItem returnItem = createMenuItem(ItemName.RETURN, new ReturnAction(), nextMenu);
        items.add(returnItem);
    }

    @Override
    public Menu getRootMenu() {
        return MENU;
    }
}
