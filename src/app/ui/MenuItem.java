package app.ui;

import app.ui.actions.ItemName;

public class MenuItem {

    private final ItemName title;
    private final Action action;
    private final Menu nextMenu;

    public MenuItem(ItemName title, Action action, Menu nextMenu) {
        this.title = title;
        this.action = action;
        this.nextMenu = nextMenu;
    }

    public void doAction(){
        action.execute();
    }

    public ItemName getTitle() {
        return title;
    }

    public Action getAction() {
        return action;
    }

    public Menu getNextMenu() {
        return nextMenu;
    }

    @Override
    public String toString() {
        return "\t"  + title.getName();
    }
}
