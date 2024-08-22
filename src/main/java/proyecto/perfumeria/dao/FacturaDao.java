package proyecto.perfumeria.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import proyecto.perfumeria.domain.Factura;


public interface FacturaDao extends JpaRepository<Factura, Long> {

}
