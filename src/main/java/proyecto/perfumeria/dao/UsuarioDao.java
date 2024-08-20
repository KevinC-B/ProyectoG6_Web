
package proyecto.perfumeria.dao;

import proyecto.perfumeria.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

//JpaRepository es la interface que tiene la bd para poder realizar instrucciónes sin usar codígo sql
public interface UsuarioDao extends JpaRepository<Usuario,Long>{
    
    public Usuario findByUsername(String username);
    
    Usuario findByUsernameAndPassword(String username, String Password);

    Usuario findByUsernameOrCorreo(String username, String correo);

    boolean existsByUsernameOrCorreo(String username, String correo);
}
