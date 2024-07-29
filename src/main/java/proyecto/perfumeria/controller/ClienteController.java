
package proyecto.perfumeria.controller;

import java.util.List;
import proyecto.perfumeria.domain.Cliente;
import proyecto.perfumeria.services.ClienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller //indicarle a la clase que va a ser clase controlador
@RequestMapping("/cliente")
public class ClienteController {
    
    @Autowired
    private ClienteService clienteService;
    
    @GetMapping("/index")
    public String listadoCliente(Model model) {
        var lista = clienteService.getClientes();
        model.addAttribute("clientes", lista);
        model.addAttribute("totalClientes", lista.size());
        return "index"; // Esto debe coincidir con el nombre de tu archivo HTML Thymeleaf
    }

    

}
