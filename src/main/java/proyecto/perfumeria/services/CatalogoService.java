package proyecto.perfumeria.services;

import proyecto.perfumeria.domain.Producto;
import java.util.List;

public interface CatalogoService {

    List<Producto> listarProductos();

    List<Producto> filtrarPorPrecio(double minPrecio, double maxPrecio); // Nuevo método
}
