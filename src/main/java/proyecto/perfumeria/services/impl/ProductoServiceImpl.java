package proyecto.perfumeria.services.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import proyecto.perfumeria.domain.Producto;
import proyecto.perfumeria.dao.ProductoDao;
import proyecto.perfumeria.services.ProductoService;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoDao productoDao;
    
    @Override
    @Transactional(readOnly = true) // Esta transacción solo será de lectura
    public List<Producto> getProductos(boolean activos) {
        var lista = productoDao.findAll();
        if (activos) { // Sólo buscar productos activos
            lista.removeIf(p -> !p.isActivo());
        }
        return lista;
    }

    @Override
    @Transactional(readOnly = true) // Esta transacción solo será de lectura
    public Producto getProducto(Producto producto) {
        return productoDao.findById(producto.getIdProducto()).orElse(null);
    }

    @Override
    @Transactional
    public void save(Producto producto) {
        productoDao.save(producto);
    }

    @Override
    @Transactional
    public void delete(Producto producto) {
        productoDao.delete(producto);
    }
}
