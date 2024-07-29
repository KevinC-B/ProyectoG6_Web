
package proyecto.perfumeria.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SesionController {
    @GetMapping("/sesion/listsesion")
    public String listarCarrito() {
        return "/sesion/listsesion"; // nombre de la plantilla Thymeleaf
    }
}
