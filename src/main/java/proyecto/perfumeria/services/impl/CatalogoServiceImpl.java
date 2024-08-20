package proyecto.perfumeria.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyecto.perfumeria.dao.CatalogoDao;
import proyecto.perfumeria.domain.Producto;
import proyecto.perfumeria.services.CatalogoService;

import java.util.List;

@Service
public class CatalogoServiceImpl implements CatalogoService {

    @Autowired
    private CatalogoDao catalogoDao;

    @Override
    public List<Producto> listarProductos() {
        return catalogoDao.findAll();
    }

    @Override
    public List<Producto> filtrarPorPrecio(double minPrecio, double maxPrecio) {
        return catalogoDao.findByPrecioBetween(minPrecio, maxPrecio);
    }
}
