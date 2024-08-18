package proyecto.perfumeria.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proyecto.perfumeria.domain.Producto;

@Repository
public interface CatalogoDao extends JpaRepository<Producto, Long> {
}
