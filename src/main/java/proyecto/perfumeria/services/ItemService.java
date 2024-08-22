package proyecto.perfumeria.services;

import proyecto.perfumeria.domain.Item;
import java.util.List;

public interface ItemService {

    List<Item> gets();

    Item get(Item item);

    void save(Item item);

    void update(Item item);

    void delete(Item item);

    double getTotal();

    void facturar();
}
