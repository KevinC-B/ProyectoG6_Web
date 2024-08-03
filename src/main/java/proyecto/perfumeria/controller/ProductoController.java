package proyecto.perfumeria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import proyecto.perfumeria.services.ProductoService;

@Controller
@RequestMapping("/producto")
public class ProductoController {
    
    @Autowired
    private ProductoService productoService;

    @GetMapping("/listado")
    public String listado(Model model) {
        var lista = productoService.getProductos(false); //Se obtienen los productos activos
        model.addAttribute("productos", lista); //Añade la lista de productos para poder verlos en la web
        model.addAttribute("totalProductos", lista.size()); //Añade el total de productos

        return "/producto/listado"; //Hace retorno de donde se visualiza la lista de productos
    }
}
