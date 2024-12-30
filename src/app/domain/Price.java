package app.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;

public class Price {

    private Long id;

    private BigDecimal price;

    private Shop shop;

//    @JsonBackReference(value = "product_prices")

    @JsonIgnore
    private Product product;

    public Price() {}

    public Price(Long id, BigDecimal price) {

        this.id = id;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
