package proyecto.perfumeria.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import proyecto.perfumeria.dao.CatalogoDao;
import proyecto.perfumeria.domain.Producto;
import proyecto.perfumeria.services.CatalogoService;

@Controller
public class CatalogoController {

    @Autowired
    private CatalogoService catalogoService;

    @GetMapping("/catalogo/listcatalogo")
    public String listarProductos(Model model) {
        model.addAttribute("productos", catalogoService.listarProductos());
        return "catalogo/listcatalogo";
    }

    @GetMapping("/catalogo/filtrar")
    public String filtrarPorPrecio(
            @RequestParam("minPrecio") double minPrecio,
            @RequestParam("maxPrecio") double maxPrecio,
            Model model) {

        model.addAttribute("productos", catalogoService.filtrarPorPrecio(minPrecio, maxPrecio));
        return "catalogo/listcatalogo";
    }
    @Autowired
    private CatalogoDao catalogoDao;

    @GetMapping("/catalogo")
    public String mostrarCatalogo(Model model) {
        List<Producto> productos = catalogoDao.findAll();
        model.addAttribute("productos", productos);
        return "catalogo/listcatalogo";
    }

    @GetMapping("/buscar")
    public String buscarProductos(@RequestParam("search") String search, Model model) {
        List<Producto> productos = catalogoDao.findByNombreProductoContainingIgnoreCase(search);
        model.addAttribute("productos", productos);
        model.addAttribute("search", search);
        return "catalogo/listcatalogo";
    }
}
