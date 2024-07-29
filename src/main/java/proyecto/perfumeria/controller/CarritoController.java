
package proyecto.perfumeria.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CarritoController {

    @GetMapping("/carrito/listcarrito")
    public String listarCarrito() {
        return "carrito/listcarrito"; // nombre de la plantilla Thymeleaf
    }
}