package proyecto.perfumeria.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import proyecto.perfumeria.domain.Venta;


public interface VentaDao extends JpaRepository<Venta, Long> {

}
