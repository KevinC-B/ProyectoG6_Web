package proyecto.perfumeria.services.impl;

import proyecto.perfumeria.dao.ClienteDao;
import proyecto.perfumeria.domain.Cliente;
import proyecto.perfumeria.services.ClienteService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service // se define como servicio una clase que espera llamados de usuarios y solo una instancia de esa cliente
public class ClienteServiceImpl implements ClienteService {

    @Autowired //Funcion crear abjetos sin decirlo
    private ClienteDao clienteDao;
    
    /*
    @Override
    public List<Cliente> getClientes() {
        return clienteDao.findAll();
    }*/
    
    @Override
    @Transactional(readOnly = true)
    public List<Cliente> getClientes() {
        var lista = clienteDao.findAll();
        return lista;
    }
    
    @Transactional(readOnly = true)
    public Cliente getCliente(Cliente cliente) {
        return clienteDao.findById(cliente.getCedula()).orElse(null);
    }
    
    @Override
    @Transactional
    public void save(Cliente cliente) {
        clienteDao.save(cliente);
    }
    
    @Override
    @Transactional
    public void delete(Cliente cliente) {
        clienteDao.delete(cliente);
    }

    @Override
    public Cliente getCliente() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
