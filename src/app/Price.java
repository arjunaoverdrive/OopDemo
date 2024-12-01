package app;

import java.math.BigDecimal;

public class Price {

    private BigDecimal price;

    private Shop shop;
    private Product product;

    public Price(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Price{" +
                "price=" + price +
                ", shop=" + shop +
                ", product=" + product +
                '}';
    }
}
