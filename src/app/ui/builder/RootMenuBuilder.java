package app.ui.builder;

import app.ui.Action;
import app.ui.Builder;
import app.ui.Menu;
import app.ui.MenuItem;
import app.ui.ItemName;
import app.ui.actions.DeserializeAction;
import app.ui.actions.SerializeAction;
import app.ui.actions.entity.PriceAction;
import app.ui.actions.entity.ProducerAction;
import app.ui.actions.entity.ProductAction;
import app.ui.actions.entity.ShopAction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RootMenuBuilder extends Builder {

    private static final Map<ItemName, Builder> BUILDERS = initBuilders();
    private static RootMenuBuilder instance;
    private static final Menu MENU = new Menu("Root menu:");

    private RootMenuBuilder() {
    }

    public static RootMenuBuilder getInstance() {
        if (instance == null) {
            instance = new RootMenuBuilder();
        }
        return instance;
    }

    public void buildMenu() {
        List<MenuItem> menuItems = createMenuItems();
        MENU.setMenuItems(menuItems);
    }

    @Override
    public Menu getRootMenu() {
        return MENU;
    }

    private List<MenuItem> createMenuItems() {
        Map<ItemName, Builder> builders = initBuilders();
        builders.values().forEach(Builder::buildMenu);

        MenuItem product = createMenuItem(ItemName.PRODUCT, new ProductAction());
        MenuItem producer = createMenuItem(ItemName.PRODUCER, new ProducerAction());
        MenuItem shop = createMenuItem(ItemName.SHOP, new ShopAction());
        MenuItem price = createMenuItem(ItemName.PRICE, new PriceAction());
        MenuItem serialize = new MenuItem(ItemName.SAVE, new SerializeAction(), MENU);
        MenuItem deserialize = new MenuItem(ItemName.LOAD, new DeserializeAction(), MENU);

        return List.of(producer, product, shop, price, serialize, deserialize);
    }

    private static Map<ItemName, Builder> initBuilders() {
        Map<ItemName, Builder> builders = new HashMap<>();

        ProducerMenuBuilder producerMenuBuilder = ProducerMenuBuilder.getInstance();
        ProductMenuBuilder productMenuBuilder = ProductMenuBuilder.getInstance();
        ShopMenuBuilder shopMenuBuilder = ShopMenuBuilder.getInstance();
        PriceMenuBuilder priceMenuBuilder = PriceMenuBuilder.getInstance();

        builders.put(ItemName.PRODUCER, producerMenuBuilder);
        builders.put(ItemName.PRODUCT, productMenuBuilder);
        builders.put(ItemName.SHOP, shopMenuBuilder);
        builders.put(ItemName.PRICE, priceMenuBuilder);

        return builders;
    }

    private MenuItem createMenuItem(ItemName name, Action action) {
        return new MenuItem(
                name,
                action,
                BUILDERS.get(name).getRootMenu()
        );
    }
}
