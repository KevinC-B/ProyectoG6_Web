package proyecto.perfumeria.services.impl;

import org.springframework.stereotype.Service;
import proyecto.perfumeria.domain.Item;
import proyecto.perfumeria.services.ItemService;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    // Lista en memoria para almacenar los items
    private List<Item> items = new ArrayList<>();

    @Override
    public List<Item> gets() {
        return new ArrayList<>(items); // Devuelve una copia para evitar modificaciones externas
    }

    @Override
    public Item get(Item item) {
        // Encuentra el item en la lista basado en el ID del producto
        return items.stream()
                .filter(i -> i.getIdProducto().equals(item.getIdProducto()))
                .findFirst()
                .orElse(null); // Retorna null si no se encuentra el item
    }

    @Override
    public void save(Item item) {
        // Verifica si el item ya existe antes de agregarlo
        if (get(item) == null) {
            items.add(item);
        }
    }

    @Override
    public void update(Item item) {
        // Encuentra el item existente y lo actualiza
        Item existingItem = get(item);
        if (existingItem != null) {
            items.remove(existingItem);
            items.add(item);
        }
    }

    @Override
    public void delete(Item item) {
        items.removeIf(i -> i.getIdProducto().equals(item.getIdProducto()));
    }

    @Override
    public double getTotal() {
        return items.stream()
                .mapToDouble(i -> i.getCantidad() * i.getPrecio())
                .sum();
    }

    @Override
    public void facturar() {
        items.clear(); // Limpia la lista
    }
}
