package app.ui.actions;

import app.ui.Action;

public class ReturnAction implements Action {

    @Override
    public void execute() {
        System.out.println("Returning...");
    }
}
