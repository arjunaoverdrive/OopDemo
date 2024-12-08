package app.ui;

import app.ui.actions.ItemName;

import java.util.Map;

public abstract class Builder{


    private static Menu rootMenu;

    public abstract void buildMenu();

    public Menu getRootMenu() {
        return rootMenu;
    }

}
