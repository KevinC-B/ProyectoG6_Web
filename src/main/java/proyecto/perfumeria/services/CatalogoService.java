package proyecto.perfumeria.services;

import java.util.List;
import proyecto.perfumeria.domain.Producto;

public interface CatalogoService {
    
    //Se obtiene los registro de la tabla cliente en un ArrayList 
    //de objetos Cliente, todos o sólo los activos
    public List<Producto> getProductos(boolean activos);
    
    //Recuperar un producto por su "id"
    public Producto getProducto(Producto producto);
}
