package proyecto.perfumeria.services.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import proyecto.perfumeria.domain.Producto;
import proyecto.perfumeria.dao.CatalogoDao;
import proyecto.perfumeria.services.CatalogoService;

@Service
public class CatalogoServiceImpl implements CatalogoService {

    @Autowired
    private CatalogoDao catalogoDao;
    
    @Override
    @Transactional(readOnly = true)
    public List<Producto> getProductos(boolean activos) {
        var lista = catalogoDao.findAll();
        if (activos) { // Sólo buscar productos activos
            lista.removeIf(p -> !p.isActivo());
        }
        return lista;
    }

    @Override
    @Transactional(readOnly = true)
    public Producto getProducto(Long idProducto) { // Cambiado a Long
        return catalogoDao.findById(idProducto).orElse(null);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Producto> getProductosByPriceRange(Double minPrice, Double maxPrice, boolean activos) {
        var lista = catalogoDao.findAll();
        if (activos) { // Sólo buscar productos activos
            lista = lista.stream().filter(Producto::isActivo).collect(Collectors.toList());
        }
        return lista.stream()
                    .filter(p -> (minPrice == null || p.getPrecio() >= minPrice) &&
                                 (maxPrice == null || p.getPrecio() <= maxPrice))
                    .collect(Collectors.toList());
    }
}
