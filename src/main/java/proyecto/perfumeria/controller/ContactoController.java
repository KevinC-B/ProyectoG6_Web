package proyecto.perfumeria.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/contacto")
public class ContactoController {

    @GetMapping("/listcontacto") 
    public String listarCarrito() {
        return "/contacto/listcontacto"; // nombre de la plantilla Thymeleaf
    }
}