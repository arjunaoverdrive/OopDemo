package app.ui.actions;

import app.domain.Price;
import app.domain.Producer;
import app.domain.Product;
import app.domain.Shop;
import app.service.PriceServiceImpl;
import app.service.ProducerServiceImpl;
import app.service.ProductServiceImpl;
import app.service.ShopServiceImpl;
import app.ui.Action;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class DeserializeAction implements Action {

    private final ProducerServiceImpl producerService = ProducerServiceImpl.getInstance();
    private final ShopServiceImpl shopService = ShopServiceImpl.getInstance();
    private final ProductServiceImpl productService = ProductServiceImpl.getInstance();
    private final PriceServiceImpl priceService = PriceServiceImpl.getInstance();
    private static final ObjectMapper MAPPER = new ObjectMapper();


    private static final String FILENAME = "data.json";

    @Override
    public void execute() {

        ObjectMapper mapper = new ObjectMapper();
        Map<String, List<Object>> allVals;
        try {
            allVals = mapper.readValue(new File(FILENAME), new TypeReference<Map<String, List<Object>>>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        List<Object> objects = allVals.get("producers");
        List<Producer> producers = deserializeClass(objects, Producer.class)
                .stream()
                .map(p -> (Producer) p)
                .toList();

        producerService.addAll(producers);
        List<Product> products = producers.stream()
                .map(Producer::getProducts)
                .flatMap(Collection::stream)
                .toList();

        productService.addAll(products);

        Set<Price> prices = products.stream()
                .map(Product::getPrices)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());

        priceService.addAll(prices);

        Set<Shop> shops = prices.stream()
                .map(Price::getShop)
                .collect(Collectors.toSet());

        shopService.addAll(shops);

    }


    List<Object> deserializeClass(List<Object> objects, Class<?> clazz) {
        List<Object> res = new ArrayList<>();

        for (Object object : objects) {
            String str = "";
            try {
                str = MAPPER.writeValueAsString(object);
                var entity = MAPPER.readValue(str, clazz);
                res.add(entity);
            } catch (JsonProcessingException e) {
                System.err.println(e.getMessage());
            }
        }
        return res;
    }
}
