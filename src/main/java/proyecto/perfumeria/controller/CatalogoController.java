package proyecto.perfumeria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import proyecto.perfumeria.domain.Producto;
import proyecto.perfumeria.services.CatalogoService;

@Controller
@RequestMapping("/catalogo")
public class CatalogoController {

    @Autowired
    private CatalogoService catalogoService;

    @GetMapping("/listcatalogo")
    public String listado(Model model) {
        var lista = catalogoService.getProductos(false); //Se obtienen los productos activos
        model.addAttribute("productos", lista); //Añade la lista de productos para poder verlos en la web
        model.addAttribute("totalProductos", lista.size()); //Añade el total de productos

        return "/catalogo/listcatalogo"; //Hace retorno de donde se visualiza la lista de productos
    }
    
    @GetMapping("/verproducto/{idProducto}")
    public String verProducto(Producto producto, Model model) {
        producto=catalogoService.getProducto(producto);
        model.addAttribute("producto", producto);
        return "/catalogo/verproducto";
    }
}