package app;

import java.util.*;

public class Product{

    private String name;
    private Double weight;

    private Producer producer;

    private Set<Price> prices = new HashSet<>();

    public Product() {
    }

    public Product(double weight) {
        this.weight = weight;
    }

    public Product(String name, double weight) {
        this.name = name;
        this.weight = weight;
    }

    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
        producer.getProducts().add(this);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Set<Price> getPrices() {
        return prices;
    }

    public void setPrices(Set<Price> prices) {
        this.prices = prices;
    }


    public void addPrice(Price price) {
        this.prices.add(price);
        price.setProduct(this);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Product product = (Product) object;
        return Objects.equals(name, product.name) && Objects.equals(weight, product.weight) && Objects.equals(producer, product.producer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, weight, producer);
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                ", producer=" + producer +
                '}';
    }

}
