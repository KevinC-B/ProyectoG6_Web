package proyecto.perfumeria.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import proyecto.perfumeria.domain.Producto;

//JpaRepository es la interface que tiene la bd para poder realizar instrucciónes sin usar codígo sql
public interface CatalogoDao extends JpaRepository<Producto,Long>{
    
    
}
