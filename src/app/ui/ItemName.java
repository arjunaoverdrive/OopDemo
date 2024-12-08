package app.ui;

public enum ItemName {

    PRODUCER("Producer"),
    PRODUCT("Product"),
    SHOP("Shop"),
    PRICE("Price"),
    RETURN("Return"),
    GET_BY_ID("Get by id"),
    CREATE("Create"),
    PRINT_ALL("Print all");

    private final String name;


    public String getName() {
        return name;
    }

    ItemName(String name) {
        this.name = name;
    }
}
