package proyecto.perfumeria.services.impl;

import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import proyecto.perfumeria.dao.UsuarioDao;
import proyecto.perfumeria.domain.Rol;
import proyecto.perfumeria.domain.Usuario;
import proyecto.perfumeria.services.UsuarioDetailsService;

@Service("userDetailsService")
public class UsuarioDetailsServiceImpl implements UsuarioDetailsService, UserDetailsService {
    
    @Autowired
    private UsuarioDao usuarioDao;
    @Autowired
    private HttpSession session;
    
    @Override
    @Transactional(readOnly=true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        //Se busca el registro en la tabla usuario que tenga el username pasado por prm
        Usuario usuario = usuarioDao.findByUsername(username);
        
        //se valida si el username se encontró
        if (usuario==null) {
            //No se encontro el username
            throw new UsernameNotFoundException(username);
        }
        
        //Si estamos acá... entonces si se econtro el username
        //Se debe cargar la información
        session.removeAttribute("imagenUsuario");
        session.setAttribute("imagenUsuario", usuario.getRutaImagen());
        
        //Se deben crear los roles que vienen desde la tabla rol...
        var roles = new ArrayList<GrantedAuthority>();
        
        //Se recorren los roles del usuario y se pasan al arreglo... ya como rol de seguridad
        for (Rol r : usuario.getRoles()) {
            roles.add(new SimpleGrantedAuthority(r.getNombre()));
        }
        
        //Se retorna un usuario del sistema con username, password y roles...
        return new User(usuario.getUsername(), usuario.getPassword(), roles);
    }
    
}
