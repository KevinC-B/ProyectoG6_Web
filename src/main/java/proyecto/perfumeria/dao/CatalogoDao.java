package proyecto.perfumeria.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import proyecto.perfumeria.domain.Producto;

import java.util.List;

public interface CatalogoDao extends JpaRepository<Producto, Long> {

    List<Producto> findByPrecioBetween(double minPrecio, double maxPrecio); // Método de consulta por rango de precios

    List<Producto> findByNombreProductoContainingIgnoreCase(String nombreProducto);
}
