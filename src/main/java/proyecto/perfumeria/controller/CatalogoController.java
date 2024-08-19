package proyecto.perfumeria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import proyecto.perfumeria.domain.Producto;
import proyecto.perfumeria.services.CatalogoService;

import java.util.List;

@Controller
@RequestMapping("/catalogo")
public class CatalogoController {

    @Autowired
    private CatalogoService catalogoService;

    @GetMapping
    public String listado(@RequestParam(value = "minPrice", required = false) Double minPrice,
                          @RequestParam(value = "maxPrice", required = false) Double maxPrice,
                          Model model) {
        List<Producto> lista = catalogoService.getProductosByPriceRange(minPrice, maxPrice, true); // Obtiene productos filtrados por precio
        model.addAttribute("productos", lista); // Añade la lista de productos para poder verlos en la web
        model.addAttribute("totalProductos", lista.size()); // Añade el total de productos

        return "catalogo/listcatalogo"; // Nombre de la plantilla Thymeleaf
    }
    
    @GetMapping("/verproducto/{idProducto}")
    public String verProducto(@PathVariable("idProducto") Long idProducto, Model model) {
        Producto producto = catalogoService.getProducto(idProducto); // Obtiene el producto por id
        model.addAttribute("producto", producto);
        return "catalogo/verproducto"; // Nombre de la plantilla Thymeleaf
    }
}