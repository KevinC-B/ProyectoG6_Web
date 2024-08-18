package proyecto.perfumeria.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proyecto.perfumeria.domain.Producto;

@Repository
public interface ProductoDao extends JpaRepository<Producto, Long> {
}
