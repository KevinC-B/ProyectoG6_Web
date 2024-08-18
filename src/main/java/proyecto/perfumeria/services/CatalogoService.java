package proyecto.perfumeria.services;

import proyecto.perfumeria.domain.Producto;
import java.util.List;

public interface CatalogoService {
    List<Producto> getProductos(boolean activos);
    Producto getProducto(Long idProducto);
    List<Producto> getProductosByPriceRange(Double minPrice, Double maxPrice, boolean activos);
}
