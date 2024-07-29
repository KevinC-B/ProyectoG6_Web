
package proyecto.perfumeria.dao;

import proyecto.perfumeria.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

//JpaRepository es la interface que tiene la bd para poder realizar instrucciónes sin usar codígo sql
public interface ClienteDao extends JpaRepository<Cliente,Long>{
    
    
}
