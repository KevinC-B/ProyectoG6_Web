
package proyecto.perfumeria.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegistroController {
    @GetMapping("/sesion/listregistro")
    public String listarCarrito() {
        return "/sesion/listregistro"; // nombre de la plantilla Thymeleaf
    }
}
