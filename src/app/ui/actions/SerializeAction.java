package app.ui.actions;

import app.domain.Producer;
import app.domain.Shop;
import app.service.ProducerServiceImpl;
import app.service.ShopServiceImpl;
import app.ui.Action;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SerializeAction implements Action {

    private final ProducerServiceImpl producerService = ProducerServiceImpl.getInstance();
    private final ShopServiceImpl shopService = ShopServiceImpl.getInstance();

    private static final String FILENAME = "data.json";

    @Override
    public void execute()  {
        List<Producer> all = producerService.getAll();

        Map<String, List<?>> map = new HashMap<>();
        map.put("producers", all);

        ObjectMapper mapper = new ObjectMapper();

        try(FileWriter writer = new FileWriter(FILENAME)) {
            mapper.writeValue(writer, map);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }
}
