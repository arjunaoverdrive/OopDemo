package app.ui.builder;

import app.ui.Action;
import app.ui.Builder;
import app.ui.Menu;
import app.ui.MenuItem;
import app.ui.actions.ItemName;

public abstract class SubMenuBuilder extends Builder {

    protected MenuItem createMenuItem(ItemName name, Action action, Menu menu) {
        return new MenuItem(
                name,
                action,
                menu
        );
    }
}
