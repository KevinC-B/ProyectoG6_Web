package proyecto.perfumeria.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InicioController {
     @GetMapping("/index")
    public String listarCarrito() {
        return "/index"; // nombre de la plantilla Thymeleaf
    }
}
