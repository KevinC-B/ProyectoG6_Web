package proyecto.perfumeria.services.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import proyecto.perfumeria.domain.Producto;
import proyecto.perfumeria.dao.CatalogoDao;
import proyecto.perfumeria.services.CatalogoService;

@Service
public class CatalogoServiceImpl implements CatalogoService {

    @Autowired
    private CatalogoDao productoDao;
    
    @Override
    @Transactional(readOnly = true)
    public List<Producto> getProductos(boolean activos) {
        var lista = productoDao.findAll();
        if (activos) { //Sólo buscar productos activos
            lista.removeIf(p -> !p.isActivo());
        }
        return lista;
    }
    
    @Override
    @Transactional(readOnly = true)
    public Producto getProducto(Producto producto) {
        return productoDao.findById(producto.getIdProducto()).orElse(null);
    }
}
