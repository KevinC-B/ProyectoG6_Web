
package proyecto.perfumeria.services;

import proyecto.perfumeria.domain.Cliente;
import java.util.List;


public interface ClienteService {
    //Se obtiene los registro de la tabla cliente en un ArrayList 
    //de objetos Cliente, todos o sólo los activos
    public List<Cliente> getClientes();
    
    //Recuperar el registro de la tabla cliente en un objeto Cliente
    // si el idCliente existe.. sino devuelve null
    public Cliente getCliente();
    
    
    //Actualiza un registro en la tabla cliente si el IdCliente existe
    //Crea un registro en la tabla cliente si id Cliente no tiene vlaor
    public void save(Cliente cliente);
    
    //Elimina el registro de la tabla cliente si idCliente existe en la tabla
    public void delete (Cliente cliente);
}



