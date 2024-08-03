package proyecto.perfumeria.services;

import java.util.List;
import proyecto.perfumeria.domain.Producto;

public interface ProductoService {
    
    //Se obtiene los registro de la tabla producto en un ArrayList 
    //de objetos Producto, todos o sólo los activos
    public List<Producto> getProductos(boolean activos);

}
